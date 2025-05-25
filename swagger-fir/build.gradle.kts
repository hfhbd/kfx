plugins {
    id("compilerModule")
}

dependencies {
    api(projects.core)
    api(projects.swaggerModel)

    testFixturesApi(libs.ktor.client.core)
    testFixturesApi(libs.ktor.client.auth)
    testFixturesApi(libs.ktor.server.core)
    testFixturesApi(libs.ktor.server.content.negotiation)
    testFixturesApi(libs.ktor.serialization.kotlinx.json)
    testFixturesApi(projects.oauth2Runtime)
    testFixturesApi(testFixtures(projects.swaggerModel))

    testImplementation(projects.kotlin)
    testImplementation(projects.ktorClient)
    testImplementation(projects.ktorServer)
    testImplementation(projects.creatorKotlinxjson)
    testImplementation(projects.irPackagename)
    testImplementation(projects.irOdata)
}

tasks.test {
    environment("testFixtures", layout.projectDirectory.dir("src/testFixtures").asFile.path)
}
