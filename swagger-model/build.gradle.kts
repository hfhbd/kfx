plugins {
    id("compilerModule")
}

dependencies {
    api(libs.serialization.json)
}

val testFixturesResources = layout.projectDirectory.dir("src/testFixtures/resources")

val downloadLeanIXOpenApi = tasks.register("downloadLeanIXOpenApi", Copy::class) {
    from(resources.text.fromUri("https://vwgroup.leanix.net/services/integration-api/v1/api-docs/swagger.json")) {
        rename { "leanix.json" }
    }
    into(testFixturesResources)
}

val updateExternalAPIs = tasks.register("updateExternalAPIs") {
    dependsOn(downloadLeanIXOpenApi)
}
