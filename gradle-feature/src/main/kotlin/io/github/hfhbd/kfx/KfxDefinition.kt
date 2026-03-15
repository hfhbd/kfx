package io.github.hfhbd.kfx

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.tasks.Nested
import org.gradle.features.binding.Definition

interface KfxDefinition : Definition<KfxBuildModel> {
    @get:Nested val openApi: NamedDomainObjectContainer<OpenApi>

    @get:Nested val swagger: NamedDomainObjectContainer<Swagger>

    @get:Nested val wsdl: NamedDomainObjectContainer<Wsdl>

    @get:Nested val xsd: NamedDomainObjectContainer<Xsd>
}
