import handler.ColorsResourceHandler
import handler.CopyFileHandler
import handler.StringsResourceHandler

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Main().execute()
        }
    }

    fun execute() {
//        FileIO.writeObjectToFile("config.json", Builder())
        FileIO.readConfig("config.json")

        CopyFileHandler.execute()
        StringsResourceHandler.execute()
        ColorsResourceHandler.execute()
    }

}