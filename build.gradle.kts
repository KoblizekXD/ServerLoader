plugins {
    kotlin("jvm") version "1.9.0"
    `maven-publish`
    `java-gradle-plugin`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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
gradlePlugin {
    plugins {
        create("serverloader") {
            id = "serverloader"
            implementationClass = "lol.koblizek.serverloader.plugin.ServerLoaderPlugin"
        }
    }
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