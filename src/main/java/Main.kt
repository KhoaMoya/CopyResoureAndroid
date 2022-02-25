import java.io.File
import kotlin.system.exitProcess

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Main().execute()
        }
    }

    fun execute() {

//        ConfigIO.writeConfig("config.json")
        ConfigIO.readConfig("config.json")

        val listResFile = readResourceFiles()

        copyToDstFolder(listResFile)
    }

    private fun copyToDstFolder(resFiles: List<File>) {
        resFiles.forEach { resFile ->
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
                getFile(resFolder, fileName)?.let {
                    listResFile.addAll(it)
                }
            }
            if (listResFile.isEmpty()) {
                System.err.println("Not file is found")
                exitProcess(1)
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