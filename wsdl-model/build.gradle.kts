plugins {
    id("compilerModule")
}

dependencies {
    api(projects.xsdModel)

    testFixturesApi(testFixtures(projects.xsdModel))
}
