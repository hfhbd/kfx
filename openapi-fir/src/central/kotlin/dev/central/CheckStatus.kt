package dev.central

import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable

@Serializable
public data class CheckStatus(
  public val deploymentId: String,
  public val deploymentName: String,
  public val deploymentState: DeploymentState,
  public val purls: List<String> = emptyList(),
  public val cherryBomUrl: String? = null,
  public val errors: Map<String, List<String>> = emptyMap(),
)
