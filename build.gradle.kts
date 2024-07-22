plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.8.10"
    id ("java")
    id("application")
}

group = "org.example"
version = "0.1.0"

repositories {
    mavenCentral()
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")

}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("app.projx.ProjxKt")
}

tasks.register<Jar>("packageFatJar") {
    manifest {
        attributes["Main-Class"] = "app.projx.ProjxKt"
    }
    archiveBaseName.set("projx-fat")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from({
        configurations.runtimeClasspath.get().filter { it.isDirectory }.map { it } +
                configurations.runtimeClasspath.get().filter { it.isFile }.map { zipTree(it) }
    })
    with(tasks.named<Jar>("jar").get())
}