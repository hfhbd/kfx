import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class SAMLUserAttributeMapper(
  /**
   * Friendly Name of the attribute to set
   */
  public val attributeFriendlyName: String,
  /**
   * Name of the SAML attribute to map to
   */
  public val attributeName: String,
  /**
   * As which data-type to set the value
   */
  public val nameFormat: SAMLUserAttributeMapperNameFormat? = null,
  /**
   * User attribute to take the value from
   */
  public val userAttribute: SAMLUserAttributeMapperUserAttribute,
)
