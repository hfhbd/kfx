package io.github.hfhbd.kfx.xsd

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

const val XSD = "http://www.w3.org/2001/XMLSchema"

@Serializable
@XmlSerialName("schema", XSD)
data class Schema(
    val elementFormDefault: String? = null,
    val targetNamespace: String,

    val attributeFormDefault: String? = null,

    @XmlElement
    @SerialName("import")
    val imports: List<Import> = emptyList(),

    @XmlElement
    @SerialName("annotation")
    val annotation: Annotation? = null,

    @XmlElement
    @SerialName("element")
    val elements: List<Element> = emptyList(),

    @XmlElement
    @SerialName("complexType")
    val complexTypes: List<ComplexType> = emptyList(),

    @XmlElement
    @SerialName("simpleType")
    val simpleType: List<SimpleType> = emptyList(),

    @XmlElement
    @SerialName("include")
    val include: Include? = null,
)

@Serializable
@XmlSerialName("include", XSD)
data class Include(
    val schemaLocation: String,
)

@Serializable
@XmlSerialName("simpleType", XSD)
data class SimpleType(
    val name: String? = null,

    @XmlElement
    @SerialName("annotation")
    val annotation: Annotation? = null,

    @XmlElement
    @SerialName("restriction")
    val restriction: Restriction,
)

@Serializable
@XmlSerialName("restriction", XSD)
data class Restriction(
    val base: String,
    @XmlElement
    @SerialName("pattern")
    val pattern: List<Pattern> = emptyList(),

    @XmlElement
    @SerialName("enumeration")
    val enumeration: List<Enumeration> = emptyList(),

    @XmlElement
    val minLength: Int? = null,

    @XmlElement
    val maxLength: Int? = null,

    @XmlElement
    val minInclusive: Int? = null,

    @XmlElement
    val maxInclusive: Int? = null,

    @XmlElement
    val fractionDigits: Int? = null,

    @XmlElement
    val totalDigits: Int? = null,

    @XmlElement
    val length: Int? = null,
)

@Serializable
@XmlSerialName("enumeration", XSD)
data class Enumeration(
    val value: String,
    @SerialName("annotation")
    val annotation: Annotation? = null,
)

@Serializable
@XmlSerialName("complexType", XSD)
data class ComplexType(
    val name: String,

    @XmlElement
    @SerialName("annotation")
    val annotation: Annotation? = null,

    @XmlElement
    @SerialName("sequence")
    val sequence: Sequence? = null,

    @SerialName("choice")
    val choice: Choice? = null,

    @XmlElement
    @SerialName("simpleContent")
    val simpleContent: SimpleContent? = null,

    @XmlElement
    @SerialName("attribute")
    val attributes: List<Attribute> = listOf(),

    @XmlElement
    @SerialName("complexContent")
    val complexContent: ComplexContent? = null,

    val mixed: Boolean = false,
)

@Serializable
@XmlSerialName("complexContent", XSD)
data class ComplexContent(
    @XmlElement
    @SerialName("extension")
    val extension: Extension,
)

@Serializable
@XmlSerialName("simpleContent", XSD)
data class SimpleContent(
    @XmlElement
    @SerialName("extension")
    val extension: Extension,
)

@Serializable
@XmlSerialName("extension", XSD)
data class Extension(
    val base: String,

    @XmlElement
    @SerialName("attribute")
    val attributes: List<Attribute> = emptyList(),

    @XmlElement
    @SerialName("sequence")
    val sequence: Sequence?,
)

@Serializable
@XmlSerialName("attribute", XSD)
data class Attribute(
    val name: String,
    val type: String? = null,

    @XmlElement(false)
    @SerialName("use")
    val use: Use = Use.Optional,

    @XmlElement
    @SerialName("annotation")
    val annotation: Annotation? = null,
)

@Serializable
@XmlSerialName("use", XSD)
enum class Use {
    @SerialName("optional")
    Optional,
    @SerialName("required")
    Required,
}

@Serializable
@XmlSerialName("sequence", XSD)
data class Sequence(
    @XmlElement
    val annotation: Annotation? = null,

    val minOccurs: String = "1",
    val maxOccurs: String = "1",
    val elements: List<Elements>,
)

@Serializable
sealed interface Elements

@Serializable
@XmlSerialName("choice", XSD)
data class Choice(
    @SerialName("element")
    val element: List<Element>,

    val minOccurs: String = "1",
    val maxOccurs: String = "1",
) : Elements

@Serializable
@XmlSerialName("element", XSD)
data class Element(
    val name: String,
    val type: String? = null,
    @XmlElement
    @SerialName("annotation")
    val annotation: Annotation? = null,

    val minOccurs: String = "1",
    val maxOccurs: String = "1",
    val nillable: Boolean = false,
    val abstract: Boolean = false,
    val default: String? = null,

    val ref: String? = null,

    @XmlElement
    @SerialName("complexType")
    val complexType: ComplexType? = null,

    @XmlElement
    @SerialName("simpleType")
    val simpleType: SimpleType? = null,
) : Elements

@Serializable
@XmlSerialName("annotation", XSD)
data class Annotation(
    @XmlElement
    @SerialName("appinfo")
    val appInfo: AppInfo? = null,

    @XmlElement
    @SerialName("documentation")
    val documentation: Documentation? = null,
)

@Serializable
@XmlSerialName("documentation", XSD)
data class Documentation(
    @XmlValue
    val values: List<@Polymorphic Any>,
) {
    companion object {
        fun serializerModule() = SerializersModule {
            polymorphic(Any::class, String::class, String.serializer())
        }
    }
}

@Serializable
@XmlSerialName("appinfo", XSD)
data class AppInfo(
    @XmlValue
    val appInfo: List<@Polymorphic Any>,
) {
    companion object {
        fun serializerModule() = SerializersModule {
            polymorphic(Any::class, String::class, String.serializer())
            polymorphic(Any::class, Pattern::class, Pattern.serializer())
        }
    }
}

@Serializable
@XmlSerialName("pattern", XSD)
data class Pattern(
    val value: String? = null,
)

@Serializable
@XmlSerialName("import", XSD)
data class Import(
    val namespace: String,
    val schemaLocation: String? = null,
)
