package ui.designsystem.asyncimage.loader

import ui.designsystem.asyncimage.model.ImageModel
import java.io.File
import java.io.InputStream
import java.net.URL

abstract class ImageLoader<T : ImageModel> {

    private fun getCacheDir(): File {
        val path = File("${System.getProperty("user.dir")}/cache")
        if (!path.exists()) {
            path.mkdir()
        }
        return path
    }

    private fun getExt(url: String): String {
        val path = url.split("?", limit = 2)[0]
        return path.split(".").last()
    }

    private fun getDiskCache(url: String): File {
        val dir = getCacheDir()
        val ext = getExt(url)
        return File(dir, "${url.hashCode()}.$ext")
    }

    private fun getDiskCacheInputStream(file: File): InputStream {
        return file.inputStream().buffered()
    }

    protected abstract fun loadLocal(inputStream: InputStream): T

    private fun getRemoteInputStream(url: String): InputStream {
        return URL(url).openStream().buffered()
    }

    private fun saveDiskCache(inputStream: InputStream, diskCache: File) {
        diskCache.outputStream().buffered().use { output ->
            inputStream.copyTo(output)
        }
    }

    fun loadImage(file: File): T {
        return loadLocal(file.inputStream().buffered())
    }

    fun loadImage(url: String): T {
        val diskCache = getDiskCache(url)
        return if (diskCache.exists()) {
            loadLocal(getDiskCacheInputStream(diskCache))
        } else {
            saveDiskCache(getRemoteInputStream(url), diskCache)
            loadLocal(getDiskCacheInputStream(diskCache))
        }
    }
}
