package dev.central

import kotlin.Double
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BrowseDeploymentsResponse")
public data class BrowseDeploymentsResponse(
  public val deployments: List<DeploymentResponseFiles>,
  public val page: Double? = null,
  public val pageSize: Double? = null,
  public val pageCount: Double? = null,
  public val totalResultCount: Double? = null,
)
