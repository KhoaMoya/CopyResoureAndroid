object Config {

    const val START_RESOURCE_TAG = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>"
    const val END_RESOURCE_TAG = "\n</resources>"

    var isCopy: Boolean = true
    var isOverwrite: Boolean = true
    var resFolderSource: String = ""
    var resFolderDestination: String = ""
    var listFileName: List<String> = emptyList()
    var listColorName: List<String> = emptyList()
    var listStringName: List<String> = emptyList()
}

data class Builder(
    val isCopy: Boolean = true,
    val isOverwrite: Boolean = true,
    val resFolderSource: String = "",
    val resFolderDestination: String = "",
    val listFileName: List<String> = emptyList(),
    var listColorName: List<String> = emptyList(),
    var listStringName: List<String> = emptyList()
) {
    fun build() {
        Config.isCopy = isCopy
        Config.isOverwrite = isOverwrite
        Config.resFolderSource = resFolderSource
        Config.resFolderDestination = resFolderDestination
        Config.listFileName = listFileName.distinct()
        Config.listColorName = listColorName.distinct()
        Config.listStringName = listStringName.distinct()
    }
}
