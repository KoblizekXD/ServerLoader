package lol.koblizek.serverloader.plugin

import lol.koblizek.serverloader.plugin.tasks.CleanDirectoryTask
import lol.koblizek.serverloader.plugin.tasks.SetupPaperServerTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class ServerLoaderPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        cleanDirectoryTask = project.tasks.register("clean", CleanDirectoryTask::class.java).get()
        setupPaperServerTask = project.tasks.register("clean", SetupPaperServerTask::class.java).get()
    }
    companion object {
        lateinit var cleanDirectoryTask: CleanDirectoryTask
        lateinit var setupPaperServerTask: SetupPaperServerTask
    }
}