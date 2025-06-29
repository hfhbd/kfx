package dev.central

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class DeploymentResponseFilesDeploymentType {
  @SerialName(value = "BUNDLE")
  Bundle,
  @SerialName(value = "SINGLE")
  Single,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
