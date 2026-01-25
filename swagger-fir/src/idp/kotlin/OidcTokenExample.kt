import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class OidcTokenExample(
  /**
   * An already decoded access token
   */
  public val accessToken: Unit? = null,
  /**
   * An already decoded id token
   */
  public val idToken: Unit? = null,
)
