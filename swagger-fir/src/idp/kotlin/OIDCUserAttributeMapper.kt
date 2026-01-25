import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class OIDCUserAttributeMapper(
  /**
   * Name of the claim to set
   */
  public val claimName: String,
  /**
   * User attribute to take the value from
   */
  public val userAttribute: OIDCUserAttributeMapperUserAttribute,
  /**
   * As which data-type to set the value
   */
  public val valueType: OIDCUserAttributeMapperValueType? = null,
)
