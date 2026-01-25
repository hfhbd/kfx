import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class Catalog(
  public val services: List<Service>,
)
