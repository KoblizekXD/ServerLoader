package lol.koblizek.serverloader.plugin.tasks

import org.gradle.api.DefaultTask
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
        if (file.exists()) deleteDir(file)
        Files.createDirectory(file.toPath())
    }
    fun deleteDir(file: File) {
        val contents = file.listFiles()
        if (contents != null) {
            for (f in contents) {
                if (!Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f)
                }
            }
        }
        file.delete()
    }
}