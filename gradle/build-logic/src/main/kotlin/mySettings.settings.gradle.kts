plugins {
    id("org.gradle.toolchains.foojay-resolver-convention")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        mavenCentral()

        exclusiveContent {
            forRepository {
                ivy(url = "https://nodejs.org/dist/") {
                    name = "Node Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("org.nodejs", "node") }
                }
            }
            filter { includeGroup("org.nodejs") }
        }
        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://raw.githubusercontent.com/Kotlin/declarative-gradle-jetbrains-ecosystem-plugin/refs/heads/maven2")
                }
            }
            filter {
                includeGroup("org.jetbrains.ecosystem")
            }
        }
    }
}
