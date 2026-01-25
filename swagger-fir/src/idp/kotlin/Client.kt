import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator(discriminator = "protocol")
public sealed interface Client {
  /**
   * Email, which can be used to announce breaking changes. Can be a semicolon separated list. Make sure this is always up-to-date so we can reach you on important topics!
   */
  public val contactEmail: String

  /**
   * Short summary about your client
   */
  public val description: String

  /**
   * The minimal authentication level
   */
  public val minRequiredAuthenticationLevel: ClientMinRequiredAuthenticationLevel?

  /**
   * Name for the idp-client. Unique per service-instance. Can NOT be changed later.
   */
  public val name: String?

  /**
   * Authentication-Realm in which the client exists. Different realms have different global settings.
   */
  public val realm: String?

  public val roleNameMappings: List<RoleNameMapping>

  public val skipCertificateConfirmation: Boolean?

  /**
   * roles
   */
  public val rolesApplicationName: List<String>

  public val rolesObjectID: List<String>

  public val rolesRoleName: List<String>
}
