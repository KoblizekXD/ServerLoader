package lol.koblizek.serverloader.plugin.tasks

import lol.koblizek.serverloader.plugin.ServerLoaderPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class SetupServerTask : DefaultTask() {
    init {
        group = "serverloader"
        description = "Downloads and prepares server based on version specified in server block"
        dependsOn(ServerLoaderPlugin.cleanDirectoryTask)
    }

    @TaskAction
    fun setup() {
        if (ServerLoaderPlugin.areInitialized()) {
            when (ServerLoaderPlugin.serverType) {
                "paper" -> {
                    ServerLoaderPlugin.setupPaperServerTask.setup()
                }

                "purpur" -> {
                    ServerLoaderPlugin.setupPurpurServerTask.setup()
                }

                else -> {
                    throw RuntimeException("Unsupported server type")
                }
            }
        } else {
            throw RuntimeException("fields must be initialized!")
        }
    }
}