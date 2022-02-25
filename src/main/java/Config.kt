object Config {
    var isCopy: Boolean = true
    var isOverwrite: Boolean = true
    var resFolderSource: String = ""
    var resFolderDestination: String = ""
    var listFileName: List<String> = emptyList()
}

data class Builder(
    val isCopy: Boolean = true,
    val isOverwrite: Boolean = true,
    val resFolderSource: String = "",
    val resFolderDestination: String = "",
    val listFileName: List<String> = emptyList()
) {
    fun build() {
        Config.isCopy = isCopy
        Config.isOverwrite = isOverwrite
        Config.resFolderSource = resFolderSource
        Config.resFolderDestination = resFolderDestination
        Config.listFileName = listFileName.distinct()
    }
}
