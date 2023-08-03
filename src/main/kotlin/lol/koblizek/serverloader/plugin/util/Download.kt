package lol.koblizek.serverloader.plugin.util

import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

class Download(url: String, saveIn: String) {
    private val file: File

    init {
        val uri = URL(url)
        uri.openStream().use {
            Channels.newChannel(it).use { rbc ->
                FileOutputStream(saveIn).use { fos ->
                    fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
                }
            }
        }
        file = File(saveIn)
    }
}