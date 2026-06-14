package io.github.hfhbd.kfx

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.features.binding.BuildModel

interface KfxBuildModel : BuildModel {
    val openApi: NamedDomainObjectContainer<OpenApiBuildModel>
}
