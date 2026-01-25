import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class Service(
  public val allow_context_updates: Boolean? = null,
  public val bindable: Boolean,
  public val binding_rotatable: Boolean? = null,
  public val bindings_retrievable: Boolean? = null,
  public val dashboard_client: DashboardClient? = null,
  public val description: String,
  public val id: String,
  public val instances_retrievable: Boolean? = null,
  public val metadata: Metadata? = null,
  public val name: String,
  public val plan_updateable: Boolean? = null,
  public val plans: List<Plan>,
  public val requires: List<ServiceRequires>,
  public val tags: List<String>,
)
