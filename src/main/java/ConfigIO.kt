import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

import java.io.PrintWriter


class ConfigIO {

    companion object {

        fun readConfig(filePath: String) {
            val content = readFile(filePath)
            Gson().fromJson(content, Builder::class.java).build()
        }

        fun writeConfig(filePath: String) {
            writeToFile(filePath, Gson().toJson(Builder()))
        }

        private fun readFile(filePath: String): String {
            val sb = StringBuilder()
            BufferedReader(FileReader(filePath)).use { br ->
                var line = br.readLine()
                while (line != null) {
                    sb.append(line).append(System.lineSeparator())
                    line = br.readLine()
                }
            }
            return sb.toString()
        }

        private fun writeToFile(filePath: String, content: String) {
            try {
                val writer = PrintWriter(filePath, "UTF-8")
                writer.println(content)
                writer.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}