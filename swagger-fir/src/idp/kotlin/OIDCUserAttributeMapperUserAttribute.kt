import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class OIDCUserAttributeMapperUserAttribute {
  @SerialName(value = "username")
  Username,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
