plugins {
    id("maven-publish")
    id("signing")
    id("io.github.hfhbd.mavencentral")
    id("app.cash.licensee")
    id("org.jetbrains.dokka")
    id("dev.detekt")
}

licensee {
    allow("Apache-2.0")
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
            register("gradle") {
                url("https://docs.gradle.org/current/kotlin-dsl")
            }
        }
    }

    dependencies {
        dokkaPlugin(versionCatalogs.named("libs").findLibrary("dokka-mermaid").get())
    }
}

publishing {
    repositories {
        maven(url = "https://maven.pkg.github.com/hfhbd/kfx") {
            name = "GitHubPackages"
            credentials(PasswordCredentials::class)
        }
        maven(url = "https://central.sonatype.com/repository/maven-snapshots/") {
            name = "mavenCentralSnapshot"
            credentials(PasswordCredentials::class)
        }
    }
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("io.github.hfhbd KFX")
            description.set("A OpenAPI/WSDL code generator")
            url.set("https://github.com/hfhbd/kfx")
            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set("hfhbd")
                    name.set("Philip Wedemann")
                    email.set("mybztg+mavencentral@icloud.com")
                }
            }
            scm {
                connection.set("scm:git://github.com/hfhbd/kfx.git")
                developerConnection.set("scm:git://github.com/hfhbd/kfx.git")
                url.set("https://github.com/hfhbd/kfx")
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        providers.gradleProperty("signingKey").orNull,
        providers.gradleProperty("signingPassword").orNull,
    )
    isRequired = providers.gradleProperty("signingKey").isPresent
    sign(publishing.publications)
}

detekt {
    parallel = true
    autoCorrect = true
    buildUponDefaultConfig = true
    ignoreFailures = providers.gradleProperty("ignoreDetektFailures").map { it.toBoolean() }.orElse(false)

    dependencies {
        detektPlugins("dev.detekt:detekt-rules-ktlint-wrapper:${detekt.toolVersion.get()}")
    }
}

tasks.register<Delete>("deleteDetektBaseline") {
    delete(tasks.detekt.flatMap { it.baseline })
}

configurations.consumable("sarif") {
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named("detekt-sarif"))
    }
    outgoing {
        artifact(tasks.detekt.flatMap { it.reports.sarif.outputLocation })
    }
}
