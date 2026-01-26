import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

/**
 * An OAUTH2-Scope, which allows an application to perform actions in another application on behalf of a user
 */
@Serializable
public data class Scope(
  /**
   * A list of clientIDs that should be included in the aud-claim of the access-token if this scope is used
   */
  public val audiences: List<String> = emptyList(),
  /**
   * What this scope does and for who it is intended
   */
  public val description: String,
  public val name: String? = null,
  public val predefinedMappers: List<String> = emptyList(),
  /**
   * Realm in which the scope exists
   */
  public val realm: String? = null,
  public val roles: List<String> = emptyList(),
  public val whitelist: List<String> = emptyList(),
)
