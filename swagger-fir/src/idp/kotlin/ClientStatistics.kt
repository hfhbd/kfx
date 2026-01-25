import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class ClientStatistics(
  /**
   * Various KPIs for the client. The exact KPIs are subject to change and can be added/removed without notice!
   */
  public val KPIs: List<ClientKPI>,
)
