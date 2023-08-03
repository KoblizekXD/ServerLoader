package lol.koblizek.serverloader.plugin.tasks

import lol.koblizek.serverloader.plugin.ServerLoaderPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files

abstract class SetupPaperServerTask : DefaultTask() {
    init {
        group = "serverloader"
        description = "Downloads and prepares Paper server"
        dependsOn(ServerLoaderPlugin.cleanDirectoryTask)
    }

    @TaskAction
    fun setup() {

    }
}