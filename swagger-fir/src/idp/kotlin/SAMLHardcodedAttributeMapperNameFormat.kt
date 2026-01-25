import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SAMLHardcodedAttributeMapperNameFormat {
  @SerialName(value = "Basic")
  Basic,
  @SerialName(value = "URI Reference")
  URIReference,
  @SerialName(value = "Unspecified")
  Unspecified,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
