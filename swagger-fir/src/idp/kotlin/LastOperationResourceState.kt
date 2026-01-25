import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class LastOperationResourceState {
  @SerialName(value = "in progress")
  InProgress,
  @SerialName(value = "succeeded")
  Succeeded,
  @SerialName(value = "failed")
  Failed,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
