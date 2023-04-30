plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.lighthouse.beep.admin"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(compose.desktop.currentOs)

    implementation("io.ktor:ktor-client-core:2.3.0")
    implementation("io.ktor:ktor-client-json:2.3.0")
    implementation("io.ktor:ktor-client-logging:2.3.0")
    implementation("io.ktor:ktor-client-serialization:2.3.0")
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}
