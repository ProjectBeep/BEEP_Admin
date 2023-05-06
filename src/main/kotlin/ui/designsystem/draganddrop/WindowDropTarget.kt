package ui.designsystem.draganddrop

import java.awt.datatransfer.DataFlavor
import java.awt.dnd.DnDConstants
import java.awt.dnd.DropTarget
import java.awt.dnd.DropTargetDragEvent
import java.awt.dnd.DropTargetDropEvent
import java.io.File

class WindowDropTarget(
    private val dragAndDropStateList: List<DragAndDropState>,
) : DropTarget() {

    override fun dragOver(dtde: DropTargetDragEvent?) {
        dtde ?: return
        val dragFiles = dtde.transferable?.getTransferData(DataFlavor.javaFileListFlavor) as? List<File> ?: return
        dragAndDropStateList.forEach {
            it.dragOver(dtde.location, dragFiles)
        }
    }

    override fun drop(dtde: DropTargetDropEvent?) {
        dragAndDropStateList.forEach {
            it.isDragOver.value = false
        }
        dtde ?: return
        dtde.acceptDrop(DnDConstants.ACTION_REFERENCE)
        val dropFiles = dtde.transferable?.getTransferData(DataFlavor.javaFileListFlavor) as? List<File> ?: return
        dragAndDropStateList.forEach {
            if (it.isDropAllow.value) {
                it.isDropAllow.value = false
                it.dropFiles(dropFiles)
            }
        }
    }
}
