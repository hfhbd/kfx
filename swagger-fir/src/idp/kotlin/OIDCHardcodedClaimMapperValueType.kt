import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class OIDCHardcodedClaimMapperValueType {
  @SerialName(value = "String")
  String,
  @SerialName(value = "long")
  Long,
  @SerialName(value = "int")
  Int,
  @SerialName(value = "boolean")
  Boolean,
  @SerialName(value = "JSON")
  Json,
  ;

  override fun toString(): kotlin.String = serializer().descriptor.getElementName(ordinal)
}
