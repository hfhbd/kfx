plugins {
    id("jvmModule")
    id("java-test-fixtures")
    id("app.softwork.validation")
}

dependencies {
    api(projects.core)
    api(projects.wsdlModel)

    testFixturesApi(libs.ktor.client.core)
    testFixturesApi(projects.ktorServerSoapPlugin)
    testFixturesApi(libs.ktor.server.core)

    testImplementation(projects.validation)
    testImplementation(projects.ktorClient)
    testImplementation(projects.ktorServer)
    testImplementation(projects.kotlin)
    testImplementation(projects.contextualDate)
}

tasks.test {
    environment("testFixtures", layout.projectDirectory.dir("src/testFixtures").asFile.path)
}
