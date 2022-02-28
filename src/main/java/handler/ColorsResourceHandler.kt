package handler

import Config
import java.io.File

object ColorsResourceHandler {

    private const val POSTFIX_COLORS_FILE = "/values/colors.xml"

    private val colorItems = mutableListOf<String>()

    fun execute() {
        readColorItems()
        writeColorItems()
    }

    private fun readColorItems() {
        File(Config.resFolderSource + POSTFIX_COLORS_FILE).forEachLine { line ->
            if (isContainColorItem(line)) {
                println(line)
                colorItems.add(line)
            }
        }
    }

    private fun writeColorItems() {
        val dstFilePath = Config.resFolderDestination + POSTFIX_COLORS_FILE
        writeToFile(
            dstFilePath,
            colorItems.joinToString(prefix = System.lineSeparator(), separator = System.lineSeparator())
        )
    }

    private fun writeToFile(filePath: String, content: String) {
        File(filePath).let { dstFile ->
            if (!dstFile.exists()) {
                dstFile.run {
                    parentFile.mkdir()
                    createNewFile()
                    writeText(Config.START_RESOURCE_TAG + Config.END_RESOURCE_TAG)
                }
            }

            val currentContent = dstFile.readText()
            val endTagResourceIndex = currentContent.lastIndexOf(Config.END_RESOURCE_TAG)

            val newContent =
                currentContent.subSequence(0, endTagResourceIndex).toString() + content + Config.END_RESOURCE_TAG

            dstFile.writeText(newContent)
        }
    }

    private fun isContainColorItem(line: String): Boolean {
        return Config.listColorName.any { line.contains("<color name=\"$it\">") }
    }
}