plugins {
    id("compilerModule")
}

dependencies {
    api(libs.serialization.json)

    testImplementation(libs.serialization.json)
}

val testFixturesResources = layout.projectDirectory.dir("src/testFixtures/resources")

val downloadGitHubApi by tasks.registering(Copy::class) {
    from(resources.text.fromUri("https://raw.githubusercontent.com/github/rest-api-description/main/descriptions/api.github.com/api.github.com.json")) {
        rename { "github.json" }
    }
    into(testFixturesResources)
}
val downloadConfluence by tasks.registering(Copy::class) {
    from(resources.text.fromUri("https://dac-static.atlassian.com/cloud/confluence/openapi-v2.v3.json")) {
        rename { "confluence.json" }
    }
    into(testFixturesResources)
}

val downloadJiraApi by tasks.registering(Copy::class) {
    from(resources.text.fromUri("https://dac-static.atlassian.com/server/jira/platform/jira_software_dc_10002_swagger.v3.json?_v=1.1061.0")) {
        rename { "jira.json" }
    }
    into(testFixturesResources)
}

val updateExternalAPIs by tasks.registering {
    dependsOn(downloadGitHubApi)
    dependsOn(downloadConfluence)
    dependsOn(downloadJiraApi)
}
