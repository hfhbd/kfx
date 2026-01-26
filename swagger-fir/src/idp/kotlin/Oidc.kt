import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "oidc")
public data class Oidc(
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
   * When public, no clientsecret is required to perform logins using this client. Clients that have the Client Credentials Flow enabled can not be public.
   */
  public val accessType: OidcAccessType? = null,
  /**
   * URL to the admin interface of the client. Set this if the client supports the adapter REST API. This REST API allows the auth server to push revocation policies and other administrative tasks. Usually this is set to the base URL of the client.
   */
  public val adminURL: String? = null,
  /**
   * Enable the [authorization code flow](https://auth0.com/docs/authenticate/login/oidc-conformant-authentication/oidc-adoption-auth-code-flow) for this client. The standard and preferred Oauth/OIDC-Flow.
   */
  public val authorizationCodeFlowEnabled: Boolean? = null,
  /**
   * Default URL to use when the auth server needs to redirect or link back to the client.
   */
  public val baseURL: String? = null,
  /**
   * Enable the [client credentials flow](https://auth0.com/docs/get-started/authentication-and-authorization-flow/client-credentials-flow). Used for machine-to-machine Authentication. (Can not be enabled for public clients).
   */
  public val clientCredentialsFlowEnabled: Boolean? = null,
  /**
   * The clientId, as used for OAUTH-Flows.
   */
  public val clientID: String? = null,
  /**
   * The clientSecret, as used for OAUTH-Flows.
   */
  public val clientSecret: String? = null,
  /**
   * Default client scopes are always applied when issuing tokens for this client. Scope mappings are always applied regardless of value of used scope parameter in OIDC Authorization request.
   */
  public val defaultClientScopes: List<String>,
  /**
   * Enable the [device authorization flow](https://auth0.com/docs/get-started/authentication-and-authorization-flow/device-authorization-flow). Used when the device that needs authentication does not have a suitable browser (Smart-Devices, CLI-Tools etc.).
   */
  public val deviceAuthorizationFlowEnabled: Boolean? = null,
  /**
   * The OIDC-Discovery-URL for this client. Used to automatically configure most OIDC-Client-Applications.
   */
  public val discoveryURL: String? = null,
  /**
   * URL for [OIDC frontchannel logout](https://openid.net/specs/openid-connect-frontchannel-1_0.html).
   */
  public val frontchannelLogoutURL: String? = null,
  /**
   * Hardcoded Mappers can be used to set a claim in the ID token & userinfo-endpoint (NOT to the access token) to a fixed value.
   */
  public val hardcodedClaimMappers: List<OIDCHardcodedClaimMapper>,
  /**
   * Enable the [implicit flow](https://auth0.com/docs/authenticate/login/oidc-conformant-authentication/oidc-adoption-implicit-flow). Has historically been used for frontend-applications that can't keep a clientSecret confidential. DEPRECATED! Use Authorization Code Flow with PKCE instead if possible!
   */
  public val implicitFlowEnabled: Boolean? = null,
  /**
   * Optional client scopes are applied when issuing tokens for this client when they are requested by scope parameter in OIDC Authorization request.
   */
  public val optionalClientScopes: List<String>,
  /**
   * Enable the [resource owner password flow](https://auth0.com/docs/get-started/authentication-and-authorization-flow/resource-owner-password-flow).
   */
  public val resourceOwnerPasswordFlowEnabled: Boolean? = null,
  /**
   * Root URL appended to relative URLs.
   */
  public val rootURL: String? = null,
  /**
   * Allowed redirect URIs for authorization code flow. Supports custom schemes. HTTP-URLs (no TLS) are only allowed for the domain "localhost". You can use a '*' as wildcard at the end of a string. (If you don't have/need a redirect URI, just disable the authorization code flow below)
   */
  public val validRedirectURIs: List<String>,
  /**
   * Allowed CORS origins. Enter the string "+" to automatically treat all validRedirectURIs as allowed CORS origins. Required when doing Token-Requests from Clientside-Javascript.
   */
  public val webOrigins: List<String>,
) : Client
