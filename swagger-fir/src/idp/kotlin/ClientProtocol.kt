import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ClientProtocol {
  @SerialName(value = "oidc")
  Oidc,
  @SerialName(value = "saml")
  Saml,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
