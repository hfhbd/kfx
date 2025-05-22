plugins {
    id("app.cash.licensee")
    id("org.jetbrains.dokka")
    id("app.softwork.serviceloader-compiler")
    id("java-test-fixtures")
    id("jvm-test-suite")
}

licensee {
    allow("Apache-2.0")
}

java {
    withSourcesJar()
    withJavadocJar()
}

dokka {
    val module = project.name
    dokkaSourceSets.configureEach {
        includes.from("README.md")
        val sourceSetName = name
        sourceLink {
            localDirectory.set(file("src/$sourceSetName/kotlin"))
            remoteUrl.set(uri("https://github.com/hfhbd/kfx/tree/main/$module/src/$sourceSetName/kotlin"))
            remoteLineSuffix.set("#L")
        }
        externalDocumentationLinks {
            register("kotlinx.coroutines") {
                url("https://kotlinlang.org/api/kotlinx.coroutines/")
            }
            register("kotlinx.serialization") {
                url("https://kotlinlang.org/api/kotlinx.serialization/")
            }
            register("kotlinx.datetime") {
                url("https://kotlinlang.org/api/kotlinx-datetime/")
                packageListUrl("https://kotlinlang.org/api/kotlinx-datetime/kotlinx-datetime/package-list")
            }
            register("ktor") {
                url("https://api.ktor.io/")
            }
        }
    }

    dependencies {
        dokkaPlugin(versionCatalogs.named("libs").findLibrary("dokka-mermaid").get())
    }
}

testing.suites.withType(JvmTestSuite::class).configureEach {
    useKotlinTest()
}
