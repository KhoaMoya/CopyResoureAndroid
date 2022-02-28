import com.google.gson.Gson
import java.io.File


object FileIO {

    fun readConfig(filePath: String) {
        val content = File(filePath).readText(Charsets.UTF_8)
        Gson().fromJson(content, Builder::class.java).build()
    }

    fun writeObjectToFile(filePath: String, ob: Any) {
        File(filePath).writeText(Gson().toJson(ob))
    }

}