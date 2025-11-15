package io.github.hfhbd.kfx

import io.github.hfhbd.kfx.openapi.OpenApi
import io.github.hfhbd.kfx.swagger.Swagger
import io.github.hfhbd.kfx.wsdl.Wsdl
import org.gradle.api.PolymorphicDomainObjectContainer
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtensionAware
import javax.inject.Inject

interface KfxExtension : PolymorphicDomainObjectContainer<Kfx>

// https://github.com/gradle/gradle/issues/30714
internal abstract class KfxExtensionImpl @Inject constructor(objects: ObjectFactory) :
    KfxExtension,
    PolymorphicDomainObjectContainer<Kfx> by
    objects.polymorphicDomainObjectContainer(Kfx::class.java).apply({
        registerBinding(OpenApi::class.java, OpenApi::class.java)
        registerBinding(Swagger::class.java, Swagger::class.java)
        registerBinding(Wsdl::class.java, Wsdl::class.java)
    }) {
    init {
        whenObjectAdded {
            (this@KfxExtensionImpl as ExtensionAware).extensions.add(it.name, it)
        }
    }
}
