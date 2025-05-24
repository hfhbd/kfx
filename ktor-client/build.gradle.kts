plugins {
    id("compilerModule")
}

dependencies {
    api(projects.kotlinPoet)
    implementation(projects.ktorShared)
}
