package lol.koblizek.serverloader.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files

abstract class CleanDirectoryTask : DefaultTask() {
    init {
        group = "serverloader"
        description = "Wipes out or creates runServer/ directory "
    }

    @TaskAction
    fun clean() {

        val file: File = File(project.projectDir.path, "/runServer")
        if (file.exists()) file.delete()
        Files.createDirectory(file.toPath())
    }
}