package lol.koblizek.serverloader.plugin.tasks

import lol.koblizek.serverloader.plugin.ServerLoaderPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.nio.file.CopyOption
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.io.path.createDirectories
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

abstract class MovePluginTask : DefaultTask() {
    init {
        mustRunAfter(project.tasks.getByName("build"))
    }

    @TaskAction
    fun move() {
        val directory = Paths.get(ServerLoaderPlugin.getRuntimeDirectory().path, "plugins")
        if (!directory.exists()) directory.createDirectory()
        val pluginFile = Paths.get(project.buildDir.path, "libs").toFile().listFiles()[0]
        Files.copy(pluginFile.toPath(), directory.resolve(pluginFile.name), StandardCopyOption.REPLACE_EXISTING)

        if (ServerLoaderPlugin.autoReload && ServerLoaderPlugin.runServerTask.state.executing) {
            ServerLoaderPlugin.runServerTask.standardOutput.write("reload confirm".toByteArray())
        }
    }
}