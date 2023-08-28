package lol.koblizek.serverloader.plugin.tasks

import lol.koblizek.serverloader.plugin.ServerLoaderPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

abstract class MovePluginTask : DefaultTask() {
    init {
        mustRunAfter(project.task("build"))
    }

    @TaskAction
    fun move() {
        val directory = Paths.get(ServerLoaderPlugin.getRuntimeDirectory().path, "runServer", "plugins")
        if (!directory.exists()) directory.createDirectories()
        val pluginFile = Paths.get(project.buildDir.path, "libs").toFile().listFiles()[0]
        Files.move(pluginFile.toPath(), directory)
    }
}