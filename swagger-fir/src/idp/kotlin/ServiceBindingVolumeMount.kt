import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceBindingVolumeMount(
  public val container_dir: String,
  public val device: ServiceBindingVolumeMountDevice,
  public val device_type: ServiceBindingVolumeMountDevice_type,
  public val driver: String,
  public val mode: ServiceBindingVolumeMountMode,
)
