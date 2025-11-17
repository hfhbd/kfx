package io.github.hfhbd.kfx.wsdl.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import nl.adaptivity.xmlutil.QName
import nl.adaptivity.xmlutil.QNameSerializer
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

public const val SOAP_NAMESPACE = "http://schemas.xmlsoap.org/wsdl/soap/"
public const val WSDL_NAMESPACE = "http://schemas.xmlsoap.org/wsdl/"
public const val XSD_NAMESPACE = "http://www.w3.org/2001/XMLSchema"

@Serializable
@XmlSerialName("definitions", WSDL_NAMESPACE)
data class WSDL(
    val name: String,
    val targetNamespace: String,
    @XmlElement
    @XmlSerialName("documentation", WSDL_NAMESPACE)
    val documentation: Documentation? = null,
    @XmlElement
    @XmlSerialName("types", WSDL_NAMESPACE)
    val types: List<Types>,
    @XmlElement
    @XmlSerialName("message", WSDL_NAMESPACE)
    val messages: List<Message>,
    @XmlElement
    @XmlSerialName("portType", WSDL_NAMESPACE)
    val portTypes: List<PortType>,
    @XmlElement
    @XmlSerialName("binding", WSDL_NAMESPACE)
    val bindings: List<Binding>,
    @XmlElement
    @XmlSerialName("service", WSDL_NAMESPACE)
    val services: List<Service>,
)

@Serializable
data class Binding(
    val name: String,
    @Serializable(QNameSerializer::class)
    val type: QName,
    @XmlElement
    @XmlSerialName("documentation", WSDL_NAMESPACE)
    val documentation: String? = null,
    @XmlElement
    @XmlSerialName("binding", SOAP_NAMESPACE)
    val binding: SoapBinding,
    @XmlElement
    @XmlSerialName("operation", WSDL_NAMESPACE)
    val operations: List<SoapOperation>,
)

@Serializable
data class SoapOperation(
    val name: String,
    @XmlElement
    @XmlSerialName("operation", SOAP_NAMESPACE)
    val operation: Operation,
    @XmlElement
    @XmlSerialName("input", WSDL_NAMESPACE)
    val input: Input,
    @XmlElement
    @XmlSerialName("output", WSDL_NAMESPACE)
    val output: Output,
    @XmlElement
    @XmlSerialName("fault", WSDL_NAMESPACE)
    val fault: Fault? = null,
) {
    @Serializable
    data class Input(
        val name: String? = null,
        @XmlElement
        @XmlSerialName("body", SOAP_NAMESPACE)
        val body: Body,
    )

    @Serializable
    data class Output(
        val name: String? = null,
        @XmlElement
        @XmlSerialName("body", SOAP_NAMESPACE)
        val body: Body,
    )

    @Serializable
    data class Fault(
        val name: String,
        @XmlElement
        @XmlSerialName("fault", SOAP_NAMESPACE)
        val fault: Fault,
    ) {
        @Serializable
        data class Fault(val name: String, val use: String)
    }

    @Serializable
    data class Body(val use: String)

    @Serializable
    data class Operation(val soapAction: String, val style: String? = null)
}

@Serializable
data class SoapBinding(val style: String, val transport: String)

@Serializable
data class PortType(
    val name: String,
    @XmlElement
    @XmlSerialName("documentation", WSDL_NAMESPACE)
    val documentation: String? = null,
    @XmlElement
    @XmlSerialName("operation", WSDL_NAMESPACE)
    val operations: List<Operation>,
)

@Serializable
data class Operation(
    val name: String,
    @XmlElement
    @XmlSerialName("documentation", WSDL_NAMESPACE)
    val documentation: String? = null,
    @XmlElement
    @XmlSerialName("input", WSDL_NAMESPACE)
    val input: OperationType,
    @XmlElement
    @XmlSerialName("output", WSDL_NAMESPACE)
    val output: OperationType,
    @XmlElement
    @XmlSerialName("fault", WSDL_NAMESPACE)
    val fault: OperationType? = null,
)

@Serializable
data class OperationType(@Serializable(QNameSerializer::class) val message: QName, val name: String)

@Serializable
data class Message(
    val name: String,
    @XmlElement
    @XmlSerialName("part", WSDL_NAMESPACE)
    val part: Part,
)

@Serializable
data class Part(val name: String, @Serializable(QNameSerializer::class) val element: QName)

@Serializable
data class Service(
    val name: String,
    @XmlElement
    @XmlSerialName("port", WSDL_NAMESPACE)
    val port: Port,
)

@Serializable
data class Port(
    val name: String,
    @Serializable(QNameSerializer::class)
    val binding: QName,
    @XmlElement
    @XmlSerialName("address", SOAP_NAMESPACE)
    val address: Address,
)

@Serializable
data class Address(val location: String)

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
    @XmlSerialName("schema", XSD_NAMESPACE)
    val schemas: List<Schema>,
)

@Serializable
data class Schema(
    val elementFormDefault: String? = null,
    val targetNamespace: String,
    val attributeFormDefault: String? = null,
    @XmlElement
    @XmlSerialName("import", XSD_NAMESPACE)
    val imports: List<Import> = emptyList(),
    @XmlElement
    @XmlSerialName("annotation", XSD_NAMESPACE)
    val annotation: Annotation? = null,
    @XmlElement
    @XmlSerialName("element", XSD_NAMESPACE)
    val elements: List<Element>,
    @XmlElement
    @XmlSerialName("complexType", XSD_NAMESPACE)
    val complexTypes: List<ComplexType>,
    @XmlElement
    @XmlSerialName("simpleType", XSD_NAMESPACE)
    val simpleType: List<SimpleType>,
    @XmlElement
    @XmlSerialName("include", XSD_NAMESPACE)
    val include: Include? = null,
)

@Serializable
data class Include(val schemaLocation: String)

@Serializable
data class SimpleType(
    val name: String? = null,
    @XmlElement
    @XmlSerialName("annotation", XSD_NAMESPACE)
    val annotation: Annotation? = null,
    @XmlElement
    @XmlSerialName("restriction", XSD_NAMESPACE)
    val restriction: Restriction,
)

@Serializable
data class Restriction(
    @Serializable(QNameSerializer::class)
    val base: QName,
    @XmlElement
    @XmlSerialName("enumeration", XSD_NAMESPACE)
    val enumeration: List<Enumeration> = emptyList(),
    @XmlElement
    @XmlSerialName("minLength", XSD_NAMESPACE)
    val minLength: MinLength? = null,
    @XmlElement
    @XmlSerialName("maxLength", XSD_NAMESPACE)
    val maxLength: MaxLength? = null,
    @XmlElement
    @XmlSerialName("minInclusive", XSD_NAMESPACE)
    val minInclusive: MinInclusive? = null,
    @XmlElement
    @XmlSerialName("maxInclusive", XSD_NAMESPACE)
    val maxInclusive: MaxInclusive? = null,
    @XmlElement
    @XmlSerialName("fractionDigits", XSD_NAMESPACE)
    val fractionDigits: FractionDigits? = null,
    @XmlElement
    @XmlSerialName("totalDigits", XSD_NAMESPACE)
    val totalDigits: TotalDigits? = null,
    @XmlElement
    @XmlSerialName("length", XSD_NAMESPACE)
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
    @XmlSerialName("annotation", XSD_NAMESPACE)
    val annotation: Annotation? = null,
)

@Serializable
data class ComplexType(
    val name: String? = null,
    @XmlElement
    @XmlSerialName("annotation", XSD_NAMESPACE)
    val annotation: Annotation? = null,
    @XmlElement
    @XmlSerialName("sequence", XSD_NAMESPACE)
    val sequence: Sequence? = null,
    @XmlElement
    @XmlSerialName("simpleContent", XSD_NAMESPACE)
    val simpleContent: SimpleContent? = null,
    @XmlElement
    @XmlSerialName("attribute", XSD_NAMESPACE)
    val attributes: List<Attribute> = emptyList(),
    @XmlElement
    @XmlSerialName("complexContent", XSD_NAMESPACE)
    val complexContent: ComplexContent? = null,
)

@Serializable
data class ComplexContent(
    @XmlElement
    @XmlSerialName("extension", XSD_NAMESPACE)
    val extension: Extension,
)

@Serializable
data class SimpleContent(
    @XmlElement
    @XmlSerialName("extension", XSD_NAMESPACE)
    val extension: Extension,
)

@Serializable
data class Extension(
    val base: String,
    @XmlElement
    @XmlSerialName("attribute", XSD_NAMESPACE)
    val attributes: List<Attribute> = emptyList(),
    @XmlElement
    @XmlSerialName("sequence", XSD_NAMESPACE)
    val sequence: Sequence?,
)

@Serializable
data class Attribute(
    val name: String,
    @Serializable(QNameSerializer::class)
    val type: QName? = null,
    val use: String? = null,
    val default: String? = null,
    @XmlElement
    @XmlSerialName("annotation", XSD_NAMESPACE)
    val annotation: Annotation? = null,
)

@Serializable
data class Sequence(
    @XmlElement
    @XmlSerialName("annotation", XSD_NAMESPACE)
    val annotation: Annotation? = null,
    @XmlSerialName("minOccurs", XSD_NAMESPACE)
    val minOccurs: String? = null,
    @XmlSerialName("maxOccurs", XSD_NAMESPACE)
    val maxOccurs: String? = null,
    val elements: List<Elements>,
)

@Serializable
sealed interface Elements

@XmlSerialName("choice", XSD_NAMESPACE)
@Serializable
data class Choice(
    @XmlSerialName("element", XSD_NAMESPACE)
    val element: Element,
) : Elements

@XmlSerialName("element", XSD_NAMESPACE)
@Serializable
data class Element(
    val name: String? = null,
    @Serializable(QNameSerializer::class)
    val type: QName? = null,
    @XmlElement
    @XmlSerialName("annotation", XSD_NAMESPACE)
    val annotation: Annotation? = null,
    val minOccurs: String? = null,
    val maxOccurs: String? = null,
    val nillable: Boolean? = null,
    @Serializable(QNameSerializer::class)
    val ref: QName? = null,
    @XmlElement
    @XmlSerialName("complexType", XSD_NAMESPACE)
    val complexType: ComplexType? = null,
    @XmlElement
    @XmlSerialName("simpleType", XSD_NAMESPACE)
    val simpleType: SimpleType? = null,
) : Elements

@Serializable
data class Annotation(
    @XmlElement
    @XmlSerialName("appinfo", XSD_NAMESPACE)
    val appInfo: AppInfo? = null,
    @XmlElement
    @XmlSerialName("documentation", XSD_NAMESPACE)
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
        }
    }
}

@Serializable
data class Import(val namespace: String, val schemaLocation: String? = null)
