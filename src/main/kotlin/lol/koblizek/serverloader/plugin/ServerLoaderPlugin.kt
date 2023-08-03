package lol.koblizek.serverloader.plugin

import lol.koblizek.serverloader.plugin.tasks.CleanDirectoryTask
import lol.koblizek.serverloader.plugin.tasks.SetupPaperServerTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class ServerLoaderPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        ServerLoaderPlugin.project = project
        cleanDirectoryTask = project.tasks.register("clean", CleanDirectoryTask::class.java).get()
        setupPaperServerTask = project.tasks.register("clean", SetupPaperServerTask::class.java).get()
    }
    companion object {
        lateinit var cleanDirectoryTask: CleanDirectoryTask
        lateinit var setupPaperServerTask: SetupPaperServerTask

        lateinit var project: Project

        lateinit var version: String

        fun areInitialized(): Boolean {
            return ::version.isInitialized
        }
        fun getRuntimeDirectory(): File {
           return File(project.projectDir.path, "/runServer")
        }
    }
}
fun server(config: ServerConfiguration.() -> Unit) {
    val cfg: ServerConfiguration = ServerConfiguration()
    cfg.config()
    ServerLoaderPlugin.version = cfg.version
}