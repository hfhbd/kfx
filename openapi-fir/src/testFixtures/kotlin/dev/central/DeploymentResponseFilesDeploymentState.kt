package dev.central

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class DeploymentResponseFilesDeploymentState {
  @SerialName(value = "PENDING")
  Pending,
  @SerialName(value = "VALIDATING")
  Validating,
  @SerialName(value = "PUBLISHING")
  Publishing,
  @SerialName(value = "PUBLISHED")
  Published,
  @SerialName(value = "FAILED")
  Failed,
}
