plugins {
    id("jvmModule")
    id("java-test-fixtures")
}

dependencies {
    api(projects.core)
    api(projects.openapiModel)

    testFixturesApi(libs.ktor.client.core)
    testFixturesApi(libs.ktor.client.auth)
    testFixturesApi(libs.ktor.server.core)
    testFixturesApi(projects.oauth2Runtime)
    testFixturesApi(testFixtures(projects.openapiModel))

    testImplementation(projects.ktorServer)
    testImplementation(projects.kotlin)
    testImplementation(projects.creatorKotlinxjson)
    testImplementation(projects.irPackagename)
    testImplementation(projects.ktorClient)
}

tasks.test {
    environment("testFixtures", layout.projectDirectory.dir("src/testFixtures").asFile.path)
}
