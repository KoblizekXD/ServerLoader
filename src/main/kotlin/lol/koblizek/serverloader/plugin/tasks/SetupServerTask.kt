package lol.koblizek.serverloader.plugin.tasks

import lol.koblizek.serverloader.plugin.ServerLoaderPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class SetupServerTask : DefaultTask() {
    init {
        group = "serverloader"
        description = "Downloads and prepares server based on version specified in server block"
    }

    @TaskAction
    fun setup() {
        if (ServerLoaderPlugin.areInitialized()) {
            if (ServerLoaderPlugin.serverType == "paper") {
                finalizedBy(ServerLoaderPlugin.setupPaperServerTask)
            } else {
                throw RuntimeException("Unsupported server type")
            }
        } else {
            throw RuntimeException("fields must be initialized!")
        }
    }
}