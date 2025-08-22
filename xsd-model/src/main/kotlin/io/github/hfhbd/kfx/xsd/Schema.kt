package io.github.hfhbd.kfx.xsd

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

const val XSD = "http://www.w3.org/2001/XMLSchema"
const val ISO = "http://purl.oclc.org/dsdl/schematron"

@Serializable
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
data class Types(
    @XmlElement
    @XmlSerialName("schema", XSD)
    val schemas: List<Schema>,
)

@Serializable
@XmlSerialName("Schema", XSD)
data class Schema(
    val elementFormDefault: String? = null,
    val targetNamespace: String,

    val attributeFormDefault: String? = null,

    @XmlElement
    @XmlSerialName("import", XSD)
    val imports: List<Import> = emptyList(),

    @XmlElement
    @XmlSerialName("annotation", XSD)
    val annotation: Annotation? = null,

    @XmlElement
    @XmlSerialName("element", XSD)
    val elements: List<Element>,

    @XmlElement
    @XmlSerialName("complexType", XSD)
    val complexTypes: List<ComplexType>,

    @XmlElement
    @XmlSerialName("simpleType", XSD)
    val simpleType: List<SimpleType>,

    @XmlElement
    @XmlSerialName("include", XSD)
    val include: Include? = null,
)

@Serializable
data class Include(
    val schemaLocation: String,
)

@Serializable
data class SimpleType(
    val name: String? = null,

    @XmlElement
    @XmlSerialName("annotation", XSD)
    val annotation: Annotation? = null,

    @XmlElement
    @XmlSerialName("restriction", XSD)
    val restriction: Restriction,
)

@Serializable
data class Restriction(
    val base: String,
    @XmlElement
    @XmlSerialName("pattern", XSD)
    val pattern: List<Pattern> = emptyList(),

    @XmlElement
    @XmlSerialName("enumeration", XSD)
    val enumeration: List<Enumeration> = emptyList(),

    @XmlElement
    @XmlSerialName("minLength", XSD)
    val minLength: MinLength? = null,

    @XmlElement
    @XmlSerialName("maxLength", XSD)
    val maxLength: MaxLength? = null,

    @XmlElement
    @XmlSerialName("minInclusive", XSD)
    val minInclusive: MinInclusive? = null,

    @XmlElement
    @XmlSerialName("maxInclusive", XSD)
    val maxInclusive: MaxInclusive? = null,

    @XmlElement
    @XmlSerialName("fractionDigits", XSD)
    val fractionDigits: FractionDigits? = null,

    @XmlElement
    @XmlSerialName("totalDigits", XSD)
    val totalDigits: TotalDigits? = null,

    @XmlElement
    @XmlSerialName("length", XSD)
    val length: Length? = null,
) {
    @Serializable
    data class MinLength(val value: Int)

    @Serializable
    data class MaxLength(val value: Int)

    @Serializable
    data class MinInclusive(val value: Int)

    @Serializable
    data class MaxInclusive(val value: Int)

    @Serializable
    data class FractionDigits(val value: Int)

    @Serializable
    data class TotalDigits(val value: Int)

    @Serializable
    data class Length(val value: Int)
}

@Serializable
data class Enumeration(
    val value: String,
    @XmlSerialName("annotation", XSD)
    val annotation: Annotation? = null,
)

@Serializable
data class ComplexType(
    val name: String? = null,

    @XmlElement
    @XmlSerialName("annotation", XSD)
    val annotation: Annotation? = null,

    @XmlElement
    @XmlSerialName("sequence", XSD)
    val sequence: Sequence? = null,

    @XmlElement
    @XmlSerialName("simpleContent", XSD)
    val simpleContent: SimpleContent? = null,

    @XmlElement
    @XmlSerialName("attribute", XSD)
    val attribute: Attribute? = null,

    @XmlElement
    @XmlSerialName("complexContent", XSD)
    val complexContent: ComplexContent? = null,
)

@Serializable
data class ComplexContent(
    @XmlElement
    @XmlSerialName("extension", XSD)
    val extension: Extension,
)

@Serializable
data class SimpleContent(
    @XmlElement
    @XmlSerialName("extension", XSD)
    val extension: Extension,
)

@Serializable
data class Extension(
    val base: String,
    @XmlElement
    @XmlSerialName("attribute", XSD)
    val attributes: List<Attribute> = emptyList(),

    @XmlElement
    @XmlSerialName("sequence", XSD)
    val sequence: Sequence?,
)

@Serializable
data class Attribute(
    val name: String,
    val type: String? = null,
    val use: String? = null,

    @XmlElement
    @XmlSerialName("annotation", XSD)
    val annotation: Annotation? = null,
)

@Serializable
data class Sequence(
    @XmlElement
    @XmlSerialName("annotation", XSD)
    val annotation: Annotation? = null,

    @XmlSerialName("minOccurs", XSD)
    val minOccurs: String? = null,

    @XmlSerialName("maxOccurs", XSD)
    val maxOccurs: String? = null,

    val elements: List<Elements>,
)

@Serializable
sealed interface Elements

@XmlSerialName("choice", XSD)
@Serializable
data class Choice(
    @XmlSerialName("element", XSD)
    val element: Element,
) : Elements

@XmlSerialName("element", XSD)
@Serializable
data class Element(
    val name: String? = null,
    val type: String? = null,
    @XmlElement
    @XmlSerialName("annotation", XSD)
    val annotation: Annotation? = null,

    val minOccurs: String? = null,
    val maxOccurs: String? = null,
    val nillable: Boolean? = null,

    val ref: String? = null,

    @XmlElement
    @XmlSerialName("complexType", XSD)
    val complexType: ComplexType? = null,

    @XmlElement
    @XmlSerialName("simpleType", XSD)
    val simpleType: SimpleType? = null,
) : Elements

@Serializable
data class Annotation(
    @XmlElement
    @XmlSerialName("appinfo", XSD)
    val appInfo: AppInfo? = null,

    @XmlElement
    @XmlSerialName("documentation", XSD)
    val documentation: Documentation? = null,
)

@Serializable
data class AppInfo(
    @XmlValue
    val appInfo: List<@Polymorphic Any>,
) {
    companion object {
        fun serializerModule() = SerializersModule {
            polymorphic(Any::class, String::class, String.serializer())
            polymorphic(Any::class, NS::class, NS.serializer())
            polymorphic(Any::class, Pattern::class, Pattern.serializer())
        }
    }
}

@Serializable
@XmlSerialName("pattern", ISO)
data class Pattern(
    val name: String? = null,
    @XmlElement
    @XmlSerialName("rule", ISO)
    val rule: Rule? = null,

    val value: String? = null,
)

@Serializable
data class Rule(
    val context: String,

    @XmlElement
    @XmlSerialName("assert", ISO)
    val assert: Assert,
)

@Serializable
data class Assert(
    @XmlElement(false)
    val test: String,

    @XmlValue
    val description: String,
)

@Serializable
@XmlSerialName("ns", ISO)
data class NS(
    val prefix: String,
    val uri: String,
)

@Serializable
data class Import(
    val namespace: String,
    val schemaLocation: String? = null,
)
