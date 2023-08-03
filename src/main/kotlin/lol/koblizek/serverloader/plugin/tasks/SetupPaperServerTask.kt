package lol.koblizek.serverloader.plugin.tasks

import com.google.gson.Gson
import com.google.gson.JsonObject
import lol.koblizek.serverloader.plugin.ServerLoaderPlugin
import lol.koblizek.serverloader.plugin.util.Download
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
        if (ServerLoaderPlugin.areInitialized()) {
            val file = Download("https://api.papermc.io/v2/projects/paper/versions/1.20.1/builds/",
                temporaryDir.path + "/builds.json").file
            val json = Gson().fromJson(file.readText(), JsonObject::class.java)
            val buildNumber: Int = json.getAsJsonArray("builds").last()
                .asJsonObject.getAsJsonPrimitive("build")
                .asInt
            val url = "https://api.papermc.io/v2/projects/paper/versions/${ServerLoaderPlugin.version}/builds/$buildNumber/downloads/paper-${ServerLoaderPlugin.version}-$buildNumber.jar"
            Download(url, ServerLoaderPlugin.getRuntimeDirectory().path)
        } else {
            throw RuntimeException("version field must be initialized!")
        }
    }
}