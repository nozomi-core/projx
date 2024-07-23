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
    implementation("org.xerial:sqlite-jdbc:3.46.0.0")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.slf4j:slf4j-nop:1.7.36")
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

tasks.register<JavaExec>("projx-shell") {
    mainClass.set("app.projx.ProjxKt")
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf("shell")
}