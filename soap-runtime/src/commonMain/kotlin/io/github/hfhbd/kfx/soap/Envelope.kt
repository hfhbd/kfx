package io.github.hfhbd.kfx.soap

import kotlinx.datetime.*
import kotlinx.serialization.*
import nl.adaptivity.xmlutil.serialization.*
import kotlin.uuid.*

@Serializable
@XmlSerialName("Envelope", "http://schemas.xmlsoap.org/soap/envelope/")
public data class Envelope<T>(
    @XmlElement
    @SerialName("Header")
    val header: Header,

    @XmlElement
    @SerialName("Body")
    val body: Body<T>,
)

@Serializable
public data class Header(
    @XmlElement
    @XmlSerialName("To", "http://www.w3.org/2005/08/addressing")
    val to: String,

    @XmlElement
    @XmlSerialName("Action", "http://www.w3.org/2005/08/addressing")
    val action: String,

    @XmlElement
    @XmlSerialName("MessageID", "http://www.w3.org/2005/08/addressing")
    val messageID: String = "urn:uuid:" + Uuid.random(),

    @XmlElement
    @XmlSerialName("Security", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
    val security: Security? = null,

    @XmlElement
    @XmlSerialName("RelatesTo", "http://www.w3.org/2005/08/addressing")
    val relatesTo: String? = null,
)

@Serializable
public data class Security(
    @XmlSerialName(
        "Timestamp",
        "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd",
    )
    val timestamp: Timestamp,

    @XmlSerialName("Assertion", "urn:oasis:names:tc:SAML:2.0:assertion")
    val assertion: Assertion,
)

@Serializable
public data class Timestamp(
    @SerialName("Id")
    val id: Uuid,
    @XmlElement
    @SerialName("Created")
    val created: Instant,
    @XmlElement
    @SerialName("Expires")
    val expires: Instant,
)

@Serializable
public data class Assertion(
    @SerialName("ID")
    val id: Uuid? = null,

    @SerialName("IssueInstant")
    val issueInstant: Instant,

    @SerialName("Version")
    val version: String,

    @XmlElement
    @SerialName("Issuer")
    val issuer: String,

    @SerialName("Subject")
    val subject: Subject,

    @SerialName("Conditions")
    val conditions: Conditions,

    @SerialName("Advice")
    val advice: Advice? = null,

    @SerialName("AuthnStatement")
    val authnStatement: AuthnStatement? = null,

    @SerialName("AttributeStatement")
    val attributeStatement: AttributeStatement? = null,
)

@Serializable
public data class Subject(
    @SerialName("NameID")
    val nameID: NameID,
    @SerialName("SubjectConfirmation")
    val subjectConfirmation: SubjectConfirmation,
)

@Serializable
public data class NameID(
    @SerialName("Format")
    val format: String,
    @SerialName("NameQualifier")
    val nameQualifier: String,
    @XmlValue
    val value: String,
)

@Serializable
public data class SubjectConfirmation(
    @SerialName("Method")
    val method: String,
)

@Serializable
public data class Conditions(
    @SerialName("NotBefore")
    val notBefore: Instant,
    @SerialName("NotOnOrAfter")
    val notOnOrAfter: Instant,
)

@Serializable
public data class Advice(
    @XmlSerialName("Assertion", "urn:oasis:names:tc:SAML:2.0:assertion")
    val assertion: Assertion,
)

@Serializable
public data class AuthnStatement(
    @SerialName("AuthnInstant")
    val authnInstant: Instant,
    @SerialName("AuthnContext")
    val authnContext: AuthnContext,
)

@Serializable
public data class AuthnContext(
    @XmlElement
    @SerialName("AuthnContextClassRef")
    val authnContextClassRef: String,
)

@Serializable
public data class AttributeStatement(
    @XmlElement
    @SerialName("Attribute")
    val attribute: List<Attribute>,
)

@Serializable
public data class Attribute(
    @SerialName("Name")
    val name: String,

    @XmlElement
    @SerialName("AttributeValue")
    val value: String,
)

@Serializable
public data class Body<T>(
    @XmlElement
    val body: T,
)
