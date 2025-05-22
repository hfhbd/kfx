plugins {
    id("jvmModule")
}

dependencies {
    api(projects.kotlinPoet)
    implementation(projects.ktorShared)
}
