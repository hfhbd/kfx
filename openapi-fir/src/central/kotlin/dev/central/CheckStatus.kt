package dev.central

import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CheckStatus")
public data class CheckStatus(
  public val deploymentId: String,
  public val deploymentName: String,
  public val deploymentState: DeploymentState,
  public val purls: List<String>,
  public val cherryBomUrl: String? = null,
  public val errors: Map<String, List<String>>? = null,
)
