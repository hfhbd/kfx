plugins {
    id("compilerModule")
}

dependencies {
    api(libs.serialization.core)
    api(libs.datetime)
    
    testFixturesApi(testFixtures(projects.wsdlFir))
}
