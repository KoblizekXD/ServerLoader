package lol.koblizek.serverloader.plugin.tasks

import com.google.gson.Gson
import com.google.gson.JsonObject
import lol.koblizek.serverloader.plugin.ServerLoaderPlugin
import lol.koblizek.serverloader.plugin.util.Download
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class SetupPurpurServerTask : DefaultTask() {
    init {
        group = "serverloader"
        description = "Downloads and prepares Purpur server"
        dependsOn(ServerLoaderPlugin.cleanDirectoryTask)
    }

    @TaskAction
    fun setup() {
        if (ServerLoaderPlugin.areInitialized()) {
            val url = "https://api.purpurmc.org/v2/purpur/${ServerLoaderPlugin.version}/latest/download"
            Download(url, ServerLoaderPlugin.getRuntimeDirectory().path + "/server.jar")
        } else {
            throw RuntimeException("fields must be initialized!")
        }
    }
}