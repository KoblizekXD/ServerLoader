package lol.koblizek.serverloader.plugin

class ServerConfiguration {
    lateinit var version: String

    /**
     * Supports following servers:
     * - PaperMC(paper)
     */
    lateinit var type: String
}