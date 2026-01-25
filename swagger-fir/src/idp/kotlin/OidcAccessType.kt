import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class OidcAccessType {
  @SerialName(value = "confidential")
  Confidential,
  @SerialName(value = "public")
  Public,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
