import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "saml")
public data class Saml(
  /**
   * Email, which can be used to announce breaking changes. Can be a semicolon separated list. Make sure this is always up-to-date so we can reach you on important topics!
   */
  public override val contactEmail: String,
  /**
   * Short summary about your client
   */
  public override val description: String,
  /**
   * The minimal authentication level
   */
  public override val minRequiredAuthenticationLevel: ClientMinRequiredAuthenticationLevel? = null,
  /**
   * Name for the idp-client. Unique per service-instance. Can NOT be changed later.
   */
  public override val name: String? = null,
  /**
   * Authentication-Realm in which the client exists. Different realms have different global settings.
   */
  public override val realm: String? = null,
  public override val roleNameMappings: List<RoleNameMapping>,
  public override val skipCertificateConfirmation: Boolean? = null,
  /**
   * roles
   */
  public override val rolesApplicationName: List<String>,
  public override val rolesObjectID: List<String>,
  public override val rolesRoleName: List<String>,
  /**
   * Default URL to use when the auth server needs to redirect or link back to the client.
   */
  public val baseURL: String? = null,
  /**
   * Canonicalization method which should be used.
   */
  public val canoncializationMethod: SamlCanoncializationMethod? = null,
  /**
   * Will the client sign their saml requests and responses? And should they be validated?
   */
  public val clientSignatureRequired: Boolean? = null,
  /**
   * Base64 encoded client signing certificate without any spaces. Request-Signatures will be validated using this.
   */
  public val clientSigningCertificate: String? = null,
  /**
   * Default client scopes are always applied when issuing tokens for this client. Scope mappings are always applied regardless of value of used scope parameter in OIDC Authorization request.
   */
  public val defaultClientScopes: List<SamlDefaultClientScopes>,
  /**
   * Should SAML assertions be encrypted with client's public key using AES?
   */
  public val encryptAssertion: Boolean? = null,
  /**
   * Base64 encoded client encryption certificate without any spaces
   */
  public val encryptionCertificate: String? = null,
  /**
   * UNIQUE identifier for this SAML-Client. It is recommended that a system entity use a URL containing its own domain name to identify itself and avoid collisions.
   */
  public val entityIdentifier: String,
  /**
   * Ignore requested Name ID subject format and use Name ID format configured below.
   */
  public val forceNameIDFormat: Boolean? = null,
  /**
   * Always use POST binding for responses.
   */
  public val forcePostBinding: Boolean? = null,
  /**
   * Hardcoded Mappers can be used to set an attribute in the SAML response to a fixed value.
   */
  public val hardcodedAttributeMappers: List<SAMLHardcodedAttributeMapper>,
  /**
   * Should a statement specifying the method and timestamp be included in login responses?
   */
  public val includeAuthnStatement: Boolean? = null,
  /**
   * SAML POST Binding URL for the client's single logout service. You can leave this blank if you are using a different binding.
   */
  public val logoutPostBindingURL: String? = null,
  /**
   * SAML Redirect Binding URL for the client's single logout service. You can leave this blank if you are using a different binding.
   */
  public val logoutRedirectBindingURL: String? = null,
  /**
   * The URL where you can download the SAML-Metadata for your client. Used for auto-configuration of client-applications.
   */
  public val metadataURL: String? = null,
  /**
   * Name ID Format which should be used.
   */
  public val nameIDFormat: SamlNameIDFormat? = null,
  /**
   * SAML POST Binding URL for the client's assertion consumer service (login responses). You can leave this blank if you do not have a URL for this binding.
   */
  public val postBindingURL: String? = null,
  /**
   * Mappers allow you to add additional user attributes to the SAML response. These are predefined mappers for the most common use-cases.
   */
  public val predefinedMappers: List<String>,
  /**
   * SAML Redirect Binding URL for the client's assertion consumer service (login responses). You can leave this blank if you do not have a URL for this binding.
   */
  public val redirectBindingURL: String? = null,
  /**
   * Root URL appended to relative URLs.
   */
  public val rootURL: String? = null,
  /**
   * Should assertions inside SAML documents be signed? This setting is not needed if document is already being signed.
   */
  public val signAssertion: Boolean? = null,
  /**
   * When turned on, Cloud IDP will sign the document using the realm’s private key.
   */
  public val signDocuments: Boolean? = null,
  public val signatureKeyName: SamlSignatureKeyName? = null,
  /**
   * URL fragment name to reference client when you want to do IDP Initiated SSO.
   */
  public val ssoURLName: String? = null,
  /**
   * User Attribute Mappers allow you to add additional user attributes to the SAML response. Use these custom mappers if no predefined mapper fits your use-case.
   */
  public val userAttributeMappers: List<SAMLUserAttributeMapper>,
  /**
   * Allowed redirect URIs for SAML-Logins. Supports custom schemes. HTTP-URLs (no TLS) are only allowed for the domain "localhost". You can use a '*' as wildcard at the end of a string. Required when Authorization Code Flow is enabled.
   */
  public val validRedirectURIs: List<String>,
) : Client
