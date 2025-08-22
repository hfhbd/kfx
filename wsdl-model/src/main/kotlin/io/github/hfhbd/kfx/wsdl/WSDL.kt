package io.github.hfhbd.kfx.wsdl

import io.github.hfhbd.kfx.xsd.Schema
import io.github.hfhbd.kfx.xsd.XSD
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

private const val SOAP = "http://schemas.xmlsoap.org/wsdl/soap/"
private const val WSA = "http://www.w3.org/2005/08/addressing"
private const val WSAM = "http://www.w3.org/2007/05/addressing/metadata"
private const val WSAW = "http://www.w3.org/2006/05/addressing/wsdl"
private const val WSDL_NS = "http://schemas.xmlsoap.org/wsdl/"
private const val WSP = "http://www.w3.org/ns/ws-policy"

@Serializable
@XmlSerialName("definitions", WSDL_NS)
data class WSDL(
    val name: String,
    val targetNamespace: String,

    @XmlElement
    @XmlSerialName("documentation", WSDL_NS)
    val documentation: Documentation? = null,

    @XmlElement
    @XmlSerialName("types", WSDL_NS)
    val types: List<Types>,

    @XmlElement
    @XmlSerialName("message", WSDL_NS)
    val messages: List<Message>,

    @XmlElement
    @XmlSerialName("portType", WSDL_NS)
    val portType: PortType,

    @XmlElement
    @XmlSerialName("binding", WSDL_NS)
    val binding: Binding,

    @XmlElement
    @XmlSerialName("service", WSDL_NS)
    val service: Service,
)

@Serializable
data class Binding(
    val name: String,
    val type: String,

    @XmlElement
    @XmlSerialName("documentation", WSDL_NS)
    val documentation: String? = null,

    @XmlElement
    @XmlSerialName("binding", SOAP)
    val binding: SoapBinding,

    @XmlElement
    @XmlSerialName("UsingAddressing", WSA)
    val usingAddressing: UsingAddressing? = null,

    @XmlElement
    @XmlSerialName("operation", WSDL_NS)
    val operations: List<SoapOperation>,
)

@Serializable
data class SoapOperation(
    val name: String,

    @XmlElement
    @XmlSerialName("operation", SOAP)
    val operation: Operation,

    @XmlElement
    @XmlSerialName("input", WSDL_NS)
    val input: Input,

    @XmlElement
    @XmlSerialName("output", WSDL_NS)
    val output: Output,

    @XmlElement
    @XmlSerialName("fault", WSDL_NS)
    val fault: Fault? = null,
) {

    @Serializable
    data class Input(
        val name: String? = null,
        @XmlElement
        @XmlSerialName("body", SOAP)
        val body: Body,
    )

    @Serializable
    data class Output(
        val name: String? = null,
        @XmlElement
        @XmlSerialName("body", SOAP)
        val body: Body,
    )

    @Serializable
    data class Fault(
        val name: String,
        @XmlElement
        @XmlSerialName("fault", SOAP)
        val fault: Fault,
    ) {
        @Serializable
        data class Fault(
            val name: String,
            val use: String,
        )
    }

    @Serializable
    data class Body(
        val use: String,
    )

    @Serializable
    data class Operation(val soapAction: String, val style: String? = null)
}

@Serializable
data class SoapBinding(
    val style: String,
    val transport: String,
)

@Serializable
data class PortType(
    val name: String,
    @XmlElement
    @XmlSerialName("documentation", WSDL_NS)
    val documentation: String? = null,
    @XmlElement
    @XmlSerialName("operation", WSDL_NS)
    val operations: List<Operation>,
)

@Serializable
data class Operation(
    val name: String,

    @XmlElement
    @XmlSerialName("documentation", WSDL_NS)
    val documentation: String? = null,

    @XmlElement
    @XmlSerialName("input", WSDL_NS)
    val input: Type,

    @XmlElement
    @XmlSerialName("output", WSDL_NS)
    val output: Type,

    @XmlElement
    @XmlSerialName("fault", WSDL_NS)
    val fault: Type? = null,
)

@Serializable
data class Type(
    val message: String,
    val name: String? = null,
    @XmlSerialName("Action", WSA)
    val action: String? = null,
)

@Serializable
data class Message(
    val name: String,
    @XmlElement
    @XmlSerialName("part", WSDL_NS)
    val part: Part,
)

@Serializable
data class Part(
    val element: String,
    val name: String,
)

@Serializable
data class Service(
    val name: String,

    @XmlElement
    @XmlSerialName("port", WSDL_NS)
    val port: Port,
)

@Serializable
data class Port(
    val binding: String,
    val name: String,

    @XmlElement
    @XmlSerialName("UsingAddressing", WSAW)
    val usingAddressing: UsingAddressing? = null,

    @XmlElement
    @XmlSerialName("Policy", WSP)
    val policy: Policy? = null,

    @XmlElement
    @XmlSerialName("address", SOAP)
    val address: Address,
)

@Serializable
data class Address(
    val location: String,
)

@Serializable
data class UsingAddressing(
    @XmlSerialName("required", WSDL_NS)
    val required: Boolean,
)

@Serializable
data class Policy(
    @XmlElement
    @XmlSerialName("Addressing", WSAM)
    val addressing: Addressing? = null,
)

@Serializable
data class Addressing(
    @XmlSerialName("Policy", WSP)
    val policy: Policy,
)

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
