package lol.koblizek.serverloader.plugin

class ServerConfiguration {
    lateinit var version: String

    /**
     * Supports following servers:
     * - PaperMC(paper)
     * - PurpurMC(purpur)
     */
    lateinit var type: String
}