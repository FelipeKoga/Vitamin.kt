plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    js(IR) {
        browser()
        binaries.library()
        useEsModules()
        generateTypeScriptDefinitions()
        version = "1.0.0"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.coroutines)
            implementation(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.core)
            implementation(libs.ktor.json)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.androidx.lifecycle.viewmodel)
        }

        jsMain.dependencies {
            implementation(libs.ktor.js)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}