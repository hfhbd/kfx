import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IRTree.Type.Builtin.BOOLEAN
import io.github.hfhbd.kfx.ir.IRTree.Type.Builtin.STRING
import io.github.hfhbd.kfx.ir.IRTree.NormalClass
import io.github.hfhbd.kfx.ir.IRTree.Member
import io.github.hfhbd.kfx.ir.IRTree.Type.LIST
import io.github.hfhbd.kfx.ir.IRTree.XmlType.Element
import io.github.hfhbd.kfx.xsd.createIr
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class Testing {
    @Test
    fun generateGradleDependencyVerification() {
        val inputStream = Testing::class.java.getResource("/gradleDependencyVerification.xsd")!!.openStream()
        val irTree = inputStream.createIr(
            emptyList(),
        ) { fail("No imports expected") }

        assertEquals(
            IRTree(
                classes = setOf(
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "Coordinates",
                        serialName = "coordinatesType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = emptyMap(),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "Trust",
                        serialName = "trustType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = emptyMap(),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "TrustedArtifacts",
                        serialName = "trusted-artifactsType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = mapOf(
                            "trust" to Member(
                                type = LIST(
                                    list = NormalClass(
                                        packageName = "org.gradle.schema.dependency_verification",
                                        packageNameSuffix = "",
                                        name = "Trust",
                                        serialName = "trustType",
                                        namespace = "https://schema.gradle.org/dependency-verification",
                                        members = emptyMap(),
                                        documentation = null,
                                        isFault = false,
                                        discriminator = null,
                                        allOf = null,
                                        deprecated = false
                                    )
                                ),
                                nullable = true,
                                serialName = "trust",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            )
                        ),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "IgnoredKey",
                        serialName = "ignored-keyType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = emptyMap(),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "IgnoredKeys",
                        serialName = "ignored-keysType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = mapOf(
                            "ignored-key" to Member(
                                type = LIST(
                                    list = NormalClass(
                                        packageName = "org.gradle.schema.dependency_verification",
                                        packageNameSuffix = "",
                                        name = "IgnoredKey",
                                        serialName = "ignored-keyType",
                                        namespace = "https://schema.gradle.org/dependency-verification",
                                        members = emptyMap(),
                                        documentation = null,
                                        isFault = false,
                                        discriminator = null,
                                        allOf = null,
                                        deprecated = false
                                    )
                                ),
                                nullable = true,
                                serialName = "ignored-key",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            )
                        ),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "TrustedKey",
                        serialName = "trusted-keyType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = mapOf(
                            "trusting" to Member(
                                type = LIST(
                                    list = NormalClass(
                                        packageName = "org.gradle.schema.dependency_verification",
                                        packageNameSuffix = "",
                                        name = "Trusting",
                                        serialName = "trustingType",
                                        namespace = "https://schema.gradle.org/dependency-verification",
                                        members = emptyMap(),
                                        documentation = null,
                                        isFault = false,
                                        discriminator = null,
                                        allOf = null,
                                        deprecated = false
                                    )
                                ),
                                nullable = true,
                                serialName = "trusting",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            )
                        ),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "Trusting",
                        serialName = "trustingType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = emptyMap(),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "TrustedKeys",
                        serialName = "trusted-keysType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = mapOf(
                            "trusted-key" to Member(
                                type = LIST(
                                    list = NormalClass(
                                        packageName = "org.gradle.schema.dependency_verification",
                                        packageNameSuffix = "",
                                        name = "TrustedKey",
                                        serialName = "trusted-keyType",
                                        namespace = "https://schema.gradle.org/dependency-verification",
                                        members = mapOf(
                                            "trusting" to Member(
                                                type = LIST(
                                                    list = NormalClass(
                                                        packageName = "org.gradle.schema.dependency_verification",
                                                        packageNameSuffix = "",
                                                        name = "Trusting",
                                                        serialName = "trustingType",
                                                        namespace = "https://schema.gradle.org/dependency-verification",
                                                        members = emptyMap(),
                                                        documentation = null,
                                                        isFault = false,
                                                        discriminator = null,
                                                        allOf = null,
                                                        deprecated = false
                                                    )
                                                ),
                                                nullable = true,
                                                serialName = "trusting",
                                                namespace = "https://schema.gradle.org/dependency-verification",
                                                documentation = null,
                                                xmlType = Element,
                                                requirements = emptyList(),
                                                isOverride = false,
                                                deprecated = false
                                            )
                                        ),
                                        documentation = null,
                                        isFault = false,
                                        discriminator = null,
                                        allOf = null,
                                        deprecated = false
                                    )
                                ),
                                nullable = true,
                                serialName = "trusted-key",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            )
                        ),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "KeyServers",
                        serialName = "key-serversType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = mapOf(
                            "key-server" to Member(
                                type = LIST(
                                    list = NormalClass(
                                        packageName = "org.gradle.schema.dependency_verification",
                                        packageNameSuffix = "",
                                        name = "KeyServer",
                                        serialName = "key-serverType",
                                        namespace = "https://schema.gradle.org/dependency-verification",
                                        members = emptyMap(),
                                        documentation = null,
                                        isFault = false,
                                        discriminator = null,
                                        allOf = null,
                                        deprecated = false
                                    )
                                ),
                                nullable = true,
                                serialName = "key-server",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            )
                        ),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "KeyServer",
                        serialName = "key-serverType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = emptyMap(),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                    NormalClass(
                        packageName = "org.gradle.schema.dependency_verification",
                        packageNameSuffix = "",
                        name = "Configuration",
                        serialName = "configurationType",
                        namespace = "https://schema.gradle.org/dependency-verification",
                        members = mapOf(
                            "verify-metadata" to Member(
                                type = BOOLEAN,
                                nullable = false,
                                serialName = "verify-metadata",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            ), "verify-signatures" to Member(
                                type = BOOLEAN,
                                nullable = false,
                                serialName = "verify-signatures",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            ), "keyring-format" to Member(
                                type = STRING,
                                nullable = true,
                                serialName = "keyring-format",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            ), "key-servers" to Member(
                                type = NormalClass(
                                    packageName = "org.gradle.schema.dependency_verification",
                                    packageNameSuffix = "",
                                    name = "KeyServers",
                                    serialName = "key-serversType",
                                    namespace = "https://schema.gradle.org/dependency-verification",
                                    members = mapOf(
                                        "key-server" to Member(
                                            type = LIST(
                                                list = NormalClass(
                                                    packageName = "org.gradle.schema.dependency_verification",
                                                    packageNameSuffix = "",
                                                    name = "KeyServer",
                                                    serialName = "key-serverType",
                                                    namespace = "https://schema.gradle.org/dependency-verification",
                                                    members = emptyMap(),
                                                    documentation = null,
                                                    isFault = false,
                                                    discriminator = null,
                                                    allOf = null,
                                                    deprecated = false
                                                )
                                            ),
                                            nullable = true,
                                            serialName = "key-server",
                                            namespace = "https://schema.gradle.org/dependency-verification",
                                            documentation = null,
                                            xmlType = Element,
                                            requirements = emptyList(),
                                            isOverride = false,
                                            deprecated = false
                                        )
                                    ),
                                    documentation = null,
                                    isFault = false,
                                    discriminator = null,
                                    allOf = null,
                                    deprecated = false
                                ),
                                nullable = true,
                                serialName = "key-servers",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            ), "trusted-artifacts" to Member(
                                type = NormalClass(
                                    packageName = "org.gradle.schema.dependency_verification",
                                    packageNameSuffix = "",
                                    name = "TrustedArtifacts",
                                    serialName = "trusted-artifactsType",
                                    namespace = "https://schema.gradle.org/dependency-verification",
                                    members = mapOf(
                                        "trust" to Member(
                                            type = LIST(
                                                list = NormalClass(
                                                    packageName = "org.gradle.schema.dependency_verification",
                                                    packageNameSuffix = "",
                                                    name = "Trust",
                                                    serialName = "trustType",
                                                    namespace = "https://schema.gradle.org/dependency-verification",
                                                    members = emptyMap(),
                                                    documentation = null,
                                                    isFault = false,
                                                    discriminator = null,
                                                    allOf = null,
                                                    deprecated = false
                                                )
                                            ),
                                            nullable = true,
                                            serialName = "trust",
                                            namespace = "https://schema.gradle.org/dependency-verification",
                                            documentation = null,
                                            xmlType = Element,
                                            requirements = emptyList(),
                                            isOverride = false,
                                            deprecated = false
                                        )
                                    ),
                                    documentation = null,
                                    isFault = false,
                                    discriminator = null,
                                    allOf = null,
                                    deprecated = false
                                ),
                                nullable = true,
                                serialName = "trusted-artifacts",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            ),

                            "ignored-keys" to Member(
                                type = NormalClass(
                                    packageName = "org.gradle.schema.dependency_verification",
                                    packageNameSuffix = "",
                                    name = "IgnoredKeys",
                                    serialName = "ignored-keysType",
                                    namespace = "https://schema.gradle.org/dependency-verification",
                                    members = mapOf(
                                        "ignored-key" to Member(
                                            type = LIST(
                                                list = NormalClass(
                                                    packageName = "org.gradle.schema.dependency_verification",
                                                    packageNameSuffix = "",
                                                    name = "IgnoredKey",
                                                    serialName = "ignored-keyType",
                                                    namespace = "https://schema.gradle.org/dependency-verification",
                                                    members = emptyMap(),
                                                    documentation = null,
                                                    isFault = false,
                                                    discriminator = null,
                                                    allOf = null,
                                                    deprecated = false
                                                )
                                            ),
                                            nullable = true,
                                            serialName = "ignored-key",
                                            namespace = "https://schema.gradle.org/dependency-verification",
                                            documentation = null,
                                            xmlType = Element,
                                            requirements = emptyList(),
                                            isOverride = false,
                                            deprecated = false
                                        )
                                    ),
                                    documentation = null,
                                    isFault = false,
                                    discriminator = null,
                                    allOf = null,
                                    deprecated = false
                                ),
                                nullable = true,
                                serialName = "ignored-keys",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            ), "trusted-keys" to Member(
                                type = NormalClass(
                                    packageName = "org.gradle.schema.dependency_verification",
                                    packageNameSuffix = "",
                                    name = "TrustedKeys",
                                    serialName = "trusted-keysType",
                                    namespace = "https://schema.gradle.org/dependency-verification",
                                    members = mapOf(
                                        "trusted-key" to Member(
                                            type = LIST(
                                                list = NormalClass(
                                                    packageName = "org.gradle.schema.dependency_verification",
                                                    packageNameSuffix = "",
                                                    name = "TrustedKey",
                                                    serialName = "trusted-keyType",
                                                    namespace = "https://schema.gradle.org/dependency-verification",
                                                    members = mapOf(
                                                        "trusting" to Member(
                                                            type = LIST(
                                                                list = NormalClass(
                                                                    packageName = "org.gradle.schema.dependency_verification",
                                                                    packageNameSuffix = "",
                                                                    name = "Trusting",
                                                                    serialName = "trustingType",
                                                                    namespace = "https://schema.gradle.org/dependency-verification",
                                                                    members = emptyMap(),
                                                                    documentation = null,
                                                                    isFault = false,
                                                                    discriminator = null,
                                                                    allOf = null,
                                                                    deprecated = false
                                                                )
                                                            ),
                                                            nullable = true,
                                                            serialName = "trusting",
                                                            namespace = "https://schema.gradle.org/dependency-verification",
                                                            documentation = null,
                                                            xmlType = Element,
                                                            requirements = emptyList(),
                                                            isOverride = false,
                                                            deprecated = false
                                                        )
                                                    ),
                                                    documentation = null,
                                                    isFault = false,
                                                    discriminator = null,
                                                    allOf = null,
                                                    deprecated = false
                                                )
                                            ),
                                            nullable = true,
                                            serialName = "trusted-key",
                                            namespace = "https://schema.gradle.org/dependency-verification",
                                            documentation = null,
                                            xmlType = Element,
                                            requirements = emptyList(),
                                            isOverride = false,
                                            deprecated = false
                                        )
                                    ),
                                    documentation = null,
                                    isFault = false,
                                    discriminator = null,
                                    allOf = null,
                                    deprecated = false
                                ),
                                nullable = true,
                                serialName = "trusted-keys",
                                namespace = "https://schema.gradle.org/dependency-verification",
                                documentation = null,
                                xmlType = Element,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false
                            )
                        ),
                        documentation = null,
                        isFault = false,
                        discriminator = null,
                        allOf = null,
                        deprecated = false
                    ),
                ),
                operations = emptySet(),
                auth = emptySet(),
            ), irTree
        )
    }
}
