package lol.koblizek.serverloader.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files

abstract class SetupPaperServerTask : DefaultTask() {
    init {
        group = "serverloader"
        description = "Downloads and prepares Paper server"

    }

    @TaskAction
    fun setup() {

    }
}