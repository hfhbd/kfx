import dev.detekt.gradle.report.ReportMergeTask

val mergeDetekt = extensions.create("mergeDetekt", MergeDetektExtension::class)

val sarifFiles = configurations.resolvable("sarifFiles") {
    fromDependencyCollector(mergeDetekt.dependencies.sarif)
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named("detekt-sarif"))
    }
}

tasks.register("mergeDetektSarif", ReportMergeTask::class) {
    input.from(sarifFiles)
    output.set(layout.buildDirectory.file("reports/detekt/detekt.sarif"))
}
