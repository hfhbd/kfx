import dev.sigstore.sign.tasks.SigstoreSignFilesTask
import org.gradle.kotlin.dsl.support.serviceOf

plugins {
    id("maven-publish")
    id("signing")
    id("io.github.hfhbd.mavencentral")
   // id("dev.sigstore.sign") https://github.com/sigstore/sigstore-java/issues/1187
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
            localDirectory = file("src/$sourceSetName/kotlin")
            remoteUrl = uri("https://github.com/hfhbd/kfx/tree/main/$module/src/$sourceSetName/kotlin")
            remoteLineSuffix = "#L"
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
            name = "io.github.hfhbd KFX"
            description = "A OpenAPI/WSDL code generator"
            url = "https://github.com/hfhbd/kfx"
            licenses {
                license {
                    name = "Apache-2.0"
                    url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                }
            }
            developers {
                developer {
                    id = "hfhbd"
                    name = "Philip Wedemann"
                    email = "mybztg+mavencentral@icloud.com"
                }
            }
            scm {
                connection = "scm:git://github.com/hfhbd/kfx.git"
                developerConnection = "scm:git://github.com/hfhbd/kfx.git"
                url = "https://github.com/hfhbd/kfx"
            }
            issueManagement {
                url = "https://github.com/hfhbd/kfx/issues"
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

// https://github.com/sigstore/sigstore-java/issues/1146
tasks.withType<SigstoreSignFilesTask>().configureEach {
    launcher = serviceOf<JavaToolchainService>().launcherFor { }
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
