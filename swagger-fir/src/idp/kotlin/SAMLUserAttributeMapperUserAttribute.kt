import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SAMLUserAttributeMapperUserAttribute {
  @SerialName(value = "username")
  Username,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
