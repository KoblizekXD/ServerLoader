package lol.koblizek.serverloader.plugin

import lol.koblizek.serverloader.plugin.tasks.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import kotlin.properties.Delegates

class ServerLoaderPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        ServerLoaderPlugin.project = project
        cleanDirectoryTask = project.tasks.create("cleanDirectories", CleanDirectoryTask::class.java)
        setupPaperServerTask = project.tasks.create("setupPaper", SetupPaperServerTask::class.java)
        setupPurpurServerTask = project.tasks.create("setupPurpur", SetupPurpurServerTask::class.java)
        setupServerTask = project.tasks.create("setupServer", SetupServerTask::class.java)
        movePluginTask = project.tasks.create("movePlugin", MovePluginTask::class.java)

        project.task("build")
            .finalizedBy(movePluginTask)

        project.tasks.register("runServer", RunServerTask::class.java)
    }
    companion object {
        lateinit var cleanDirectoryTask: CleanDirectoryTask
        lateinit var setupPaperServerTask: SetupPaperServerTask
        lateinit var setupPurpurServerTask: SetupPurpurServerTask
        lateinit var setupServerTask: SetupServerTask
        lateinit var movePluginTask: MovePluginTask

        lateinit var project: Project

        lateinit var version: String
        lateinit var serverType: String
        var autoMove: Boolean = true
        var autoReload: Boolean = false

        fun areInitialized(): Boolean {
            return ::version.isInitialized
        }
        fun getRuntimeDirectory(): File {
           return File(project.projectDir.path, "/runServer")
        }
    }
}
fun server(config: ServerConfiguration.() -> Unit) {
    val cfg = ServerConfiguration()
    cfg.config()
    ServerLoaderPlugin.version = cfg.version
    ServerLoaderPlugin.serverType = cfg.type
    ServerLoaderPlugin.autoMove = cfg.autoMove
    ServerLoaderPlugin.autoReload = cfg.autoReload
}