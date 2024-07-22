plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.8.10"
    id ("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

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