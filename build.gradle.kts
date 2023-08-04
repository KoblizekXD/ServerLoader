plugins {
    kotlin("jvm") version "1.9.0"
    `maven-publish`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.1.0"
}

group = "lol.koblizek"
version = "1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(gradleApi())
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "lol.koblizek"
            artifactId = "serverloader"
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}
gradlePlugin {
    website = "https://github.com/KoblizekXD/ServerLoader"
    vcsUrl = "https://github.com/KoblizekXD/ServerLoader"

    plugins {
        create("serverloader") {
            id = "lol.koblizek.serverloader"
            displayName = "Server Loader"
            description = "Gradle plugin for quick Minecraft server setup"
            tags.set(listOf("minecraft", "server", "papermc", "plugins"))
            implementationClass = "lol.koblizek.serverloader.plugin.ServerLoaderPlugin"
        }
    }
}