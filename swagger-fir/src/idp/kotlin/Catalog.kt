import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class Catalog(
  public val services: List<Service> = emptyList(),
)
