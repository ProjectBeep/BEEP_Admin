plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("com.google.devtools.ksp").version("1.8.20-1.0.11")
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

    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")

    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}
