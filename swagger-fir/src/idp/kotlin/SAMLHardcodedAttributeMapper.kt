import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class SAMLHardcodedAttributeMapper(
  /**
   * Friendly Name of the attribute to set
   */
  public val attributeFriendlyName: String,
  /**
   * Name of the attribute to set
   */
  public val attributeName: String,
  /**
   * As which data-type to set the value
   */
  public val nameFormat: SAMLHardcodedAttributeMapperNameFormat? = null,
  /**
   * Value to set for the claim
   */
  public val `value`: String,
)
