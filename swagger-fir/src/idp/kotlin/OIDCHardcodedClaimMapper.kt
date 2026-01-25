import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class OIDCHardcodedClaimMapper(
  /**
   * Name of the claim to set
   */
  public val claimName: String,
  /**
   * Value to set for the claim
   */
  public val `value`: String,
  /**
   * As which data-type to set the value
   */
  public val valueType: OIDCHardcodedClaimMapperValueType? = null,
)
