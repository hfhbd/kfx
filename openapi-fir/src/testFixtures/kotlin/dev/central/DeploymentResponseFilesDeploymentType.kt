package dev.central

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class DeploymentResponseFilesDeploymentType {
  @SerialName(value = "BUNDLE")
  Bundle,
  @SerialName(value = "SINGLE")
  Single,
}
