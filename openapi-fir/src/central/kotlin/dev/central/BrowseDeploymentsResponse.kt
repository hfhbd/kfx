package dev.central

import kotlin.Double
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class BrowseDeploymentsResponse(
  public val deployments: List<DeploymentResponseFiles> = emptyList(),
  public val page: Double? = null,
  public val pageSize: Double? = null,
  public val pageCount: Double? = null,
  public val totalResultCount: Double? = null,
)
