package io.github.hfhbd.kfx.wsdl.model

import io.github.hfhbd.kfx.xsd.model.Documentation
import io.github.hfhbd.kfx.xsd.model.Schema
import io.github.hfhbd.kfx.xsd.model.XSD_NAMESPACE
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.QName
import nl.adaptivity.xmlutil.QNameSerializer
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

public const val SOAP_NAMESPACE = "http://schemas.xmlsoap.org/wsdl/soap/"
public const val WSDL_NAMESPACE = "http://schemas.xmlsoap.org/wsdl/"

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
data class OperationType(@Serializable(QNameSerializer::class) val message: QName, val name: String? = null)

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
data class Types(
    @XmlElement
    @XmlSerialName("schema", XSD_NAMESPACE)
    val schemas: List<Schema>,
)
