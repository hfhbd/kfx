package dev.central

import kotlin.Double
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DeploymentResponseFiles")
public data class DeploymentResponseFiles(
  public val deploymentId: String? = null,
  public val deploymentName: String? = null,
  public val deploymentState: DeploymentState? = null,
  public val deploymentType: DeploymentResponseFilesDeploymentType? = null,
  public val createTimestamp: Double? = null,
  public val purls: List<String>,
  public val deployedComponentVersions: List<DeployedComponentVersion>,
)
