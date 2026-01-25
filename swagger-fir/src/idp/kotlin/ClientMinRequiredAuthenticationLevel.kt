import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ClientMinRequiredAuthenticationLevel {
  @SerialName(value = "default")
  Default,
  @SerialName(value = "aal1")
  Aal1,
  @SerialName(value = "aal2")
  Aal2,
  @SerialName(value = "aal3")
  Aal3,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
