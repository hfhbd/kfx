package dev.central

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class DeploymentState {
  @SerialName(value = "PENDING")
  Pending,
  @SerialName(value = "VALIDATING")
  Validating,
  @SerialName(value = "VALIDATED")
  Validated,
  @SerialName(value = "PUBLISHING")
  Publishing,
  @SerialName(value = "PUBLISHED")
  Published,
  @SerialName(value = "FAILED")
  Failed,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
