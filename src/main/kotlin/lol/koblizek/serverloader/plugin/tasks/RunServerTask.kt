package lol.koblizek.serverloader.plugin.tasks

import lol.koblizek.serverloader.plugin.ServerLoaderPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.TaskAction

abstract class RunServerTask : JavaExec() {
    init {
        group = "serverloader"
        classpath = project.files(project.projectDir.path + "/runServer/server.jar")
        workingDir = ServerLoaderPlugin.getRuntimeDirectory()
        jvmArgs("-Xmx2G")
        args("-nogui")
        setStandardInput(System.`in`)
    }
}