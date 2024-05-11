plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "dev.tonimatas"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {

}

tasks.withType<Jar> {
    manifest.attributes(
        "Main-Class" to "dev.tonimatas.game.Main"
    )
}

application {
    mainClass = "dev.tonimatas.game.Main"
}

kotlin {
    jvmToolchain(21)
}