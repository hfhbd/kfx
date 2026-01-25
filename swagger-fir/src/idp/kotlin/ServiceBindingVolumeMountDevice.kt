import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceBindingVolumeMountDevice(
  public val mount_config: Unit? = null,
  public val volume_id: String,
)
