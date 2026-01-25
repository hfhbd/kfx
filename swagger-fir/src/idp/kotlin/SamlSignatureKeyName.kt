import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SamlSignatureKeyName {
  @SerialName(value = "NONE")
  None,
  @SerialName(value = "KEY_ID")
  KeyId,
  @SerialName(value = "CERT_SUBJECT")
  CertSubject,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
