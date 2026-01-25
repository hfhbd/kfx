import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SamlCanoncializationMethod {
  @SerialName(value = "EXCLUSIVE")
  Exclusive,
  @SerialName(value = "EXCLUSIVE_WITH_COMMENTS")
  ExclusiveWithComments,
  @SerialName(value = "INCLUSIVE")
  Inclusive,
  @SerialName(value = "INCLUSIVE_WITH_COMMENTS")
  InclusiveWithComments,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
