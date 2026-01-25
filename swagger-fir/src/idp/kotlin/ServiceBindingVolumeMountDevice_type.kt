import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ServiceBindingVolumeMountDevice_type {
  @SerialName(value = "shared")
  Shared,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
