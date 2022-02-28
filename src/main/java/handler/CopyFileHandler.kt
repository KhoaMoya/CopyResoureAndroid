package handler

import Config
import java.io.File
import kotlin.system.exitProcess

object CopyFileHandler {

    fun execute() {

        val listResFile = readResourceFiles()

        copyToDstFolder(listResFile)
    }

    private fun copyToDstFolder(resFiles: List<File>) {
        resFiles.forEach { resFile ->
            println(resFile.absoluteFile)
            resFile.copyTo(
                target = makeDstFileIfNotExists(resFile),
                overwrite = Config.isOverwrite
            )
        }
    }

    private fun makeDstFileIfNotExists(resFile: File): File {
        val dstFilePath = resFile.path.replaceFirst(Config.resFolderSource, Config.resFolderDestination)
        val dstFile = File(dstFilePath)
        if (!dstFile.exists()) {
            dstFile.parentFile.mkdir()
            dstFile.createNewFile()
        }
        return dstFile
    }

    private fun readResourceFiles(): List<File> {
        val resFolder = File(Config.resFolderSource)
        if (resFolder.exists()) {
            val listResFile = mutableListOf<File>()
            Config.listFileName.forEach { fileName ->
                listResFile.addAll(getFile(resFolder, fileName))
            }
            return listResFile
        } else {
            System.err.println("Resource folder is not exists")
            exitProcess(1)
        }
    }

    private fun getFile(resFolder: File, fileName: String): List<File> {
        return resFolder.walk().filter { it.nameWithoutExtension == fileName }.toList()
    }

}