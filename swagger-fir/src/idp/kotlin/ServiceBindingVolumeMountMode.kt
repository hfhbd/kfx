import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ServiceBindingVolumeMountMode {
  @SerialName(value = "r")
  R,
  @SerialName(value = "rw")
  Rw,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
