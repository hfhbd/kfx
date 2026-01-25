import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SamlDefaultClientScopes {
  @SerialName(value = "ad_saml")
  AdSaml,
  @SerialName(value = "role_list")
  RoleList,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
