package dev.central

import kotlin.Double
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class DeploymentResponseFiles(
  public val deploymentId: String? = null,
  public val deploymentName: String? = null,
  public val deploymentState: DeploymentState? = null,
  public val deploymentType: DeploymentResponseFilesDeploymentType? = null,
  public val createTimestamp: Double? = null,
  public val purls: List<String> = emptyList(),
  public val deployedComponentVersions: List<DeployedComponentVersion> = emptyList(),
)
