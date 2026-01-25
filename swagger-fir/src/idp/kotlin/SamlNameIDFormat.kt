import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SamlNameIDFormat {
  @SerialName(value = "username")
  Username,
  @SerialName(value = "email")
  Email,
  @SerialName(value = "transient")
  Transient,
  @SerialName(value = "persistent")
  Persistent,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
