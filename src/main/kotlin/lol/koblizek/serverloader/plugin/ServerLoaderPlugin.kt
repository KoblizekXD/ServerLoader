package lol.koblizek.serverloader.plugin

import lol.koblizek.serverloader.plugin.tasks.CleanDirectoryTask
import lol.koblizek.serverloader.plugin.tasks.RunServerTask
import lol.koblizek.serverloader.plugin.tasks.SetupPaperServerTask
import lol.koblizek.serverloader.plugin.tasks.SetupServerTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class ServerLoaderPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        ServerLoaderPlugin.project = project
        cleanDirectoryTask = project.tasks.register("cleanDirectories", CleanDirectoryTask::class.java).get()
        setupPaperServerTask = project.tasks.register("setupPaper", SetupPaperServerTask::class.java).get()
        setupServerTask = project.tasks.register("setupPaper", SetupServerTask::class.java).get()
        project.tasks.register("runServer", RunServerTask::class.java)
    }
    companion object {
        lateinit var cleanDirectoryTask: CleanDirectoryTask
        lateinit var setupPaperServerTask: SetupPaperServerTask
        lateinit var setupServerTask: SetupServerTask

        lateinit var project: Project

        lateinit var version: String
        lateinit var serverType: String

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
    ServerLoaderPlugin.serverType = cfg.type
}