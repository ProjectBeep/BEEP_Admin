package ui.designsystem.asyncimage

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.unit.Density
import org.succlz123.lib.imageloader.cache.core.LruCache
import org.xml.sax.InputSource
import java.io.BufferedInputStream
import java.io.File
import java.io.IOException
import java.net.URL

object AsyncImageCache {

    private val memoryCache = LruCache<String, ImageBitmap>(30)

    suspend fun getImage(url: String): ImageState {
        return try {
            val memory = memoryCache.get(url)
            if (memory != null) {
                return ImageState.Success(BitmapPainter(memory))
            }
            val diskCache = getDiskCache(url)
            val image = if (diskCache.exists()) {
                loadImageBitmap(diskCache)
            } else {
                loadImageBitmap(url)
            }.also {
                memoryCache.put(url, it)
            }
            ImageState.Success(BitmapPainter(image))
        } catch (e: IOException) {
            ImageState.Failed(e)
        }
    }

    private fun getCacheDir(): File {
        val path = File("${System.getProperty("user.dir")}/cache")
        if (!path.exists()) {
            path.mkdir()
        }
        return path
    }

    private fun getDiskCache(url: String): File {
        val dir = getCacheDir()
        return File(dir, "${url.hashCode()}")
    }

    private fun diskCache(url: String, input: BufferedInputStream) {
        try {
            getDiskCache(url).outputStream().use { output ->
                input.copyTo(output)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadImageBitmap(file: File): ImageBitmap =
        file.inputStream().buffered().use { input ->
            loadImageBitmap(input)
        }

    private fun loadImageBitmap(url: String): ImageBitmap {
        URL(url).openStream().buffered().use { input ->
            diskCache(url, input)
        }
        return loadImageBitmap(getDiskCache(url))
    }

    private fun loadSvgPainter(file: File, density: Density): Painter =
        file.inputStream().buffered().use {
            loadSvgPainter(it, density)
        }

    private fun loadXmlImageVector(file: File, density: Density): ImageVector =
        file.inputStream().buffered().use {
            loadXmlImageVector(InputSource(it), density)
        }

    private fun loadSvgPainter(url: String, density: Density): Painter =
        URL(url).openStream().buffered().use {
            loadSvgPainter(it, density)
        }

    private fun loadXmlImageVector(url: String, density: Density): ImageVector =
        URL(url).openStream().buffered().use {
            loadXmlImageVector(InputSource(it), density)
        }
}
