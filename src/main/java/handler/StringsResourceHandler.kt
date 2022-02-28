package handler

import Config
import java.io.File

object StringsResourceHandler {

    private const val POSTFIX_EN_STRING_FILE = "/values/strings.xml"
    private const val POSTFIX_VI_STRING_FILE = "/values-vi/strings.xml"

    private val stringEnItems = mutableListOf<String>()
    private val stringViItems = mutableListOf<String>()

    fun execute() {
        readStringItems()
        writeStringItems()
    }

    private fun readStringItems() {
        File(Config.resFolderSource + POSTFIX_EN_STRING_FILE).forEachLine { line ->
            if (isContainStringItem(line)) {
                println(line)
                stringEnItems.add(line)
            }
        }

        File(Config.resFolderSource + POSTFIX_VI_STRING_FILE).forEachLine { line ->
            if (isContainStringItem(line)) {
                println(line)
                stringViItems.add(line)
            }
        }
    }

    private fun writeStringItems() {
        val dstEnFilePath = Config.resFolderDestination + POSTFIX_EN_STRING_FILE
        writeToFile(
            dstEnFilePath,
            stringEnItems.joinToString(prefix = System.lineSeparator(), separator = System.lineSeparator())
        )

        val dstViFilePath = Config.resFolderDestination + POSTFIX_VI_STRING_FILE
        writeToFile(
            dstViFilePath,
            stringViItems.joinToString(prefix = System.lineSeparator(), separator = System.lineSeparator())
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

    private fun isContainStringItem(line: String): Boolean {
        return Config.listStringName.any { line.contains("<string name=\"$it\"") }
    }
}