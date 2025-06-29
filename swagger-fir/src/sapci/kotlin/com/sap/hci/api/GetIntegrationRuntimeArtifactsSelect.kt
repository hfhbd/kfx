package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationRuntimeArtifactsSelect {
  @SerialName(value = "Id")
  Id,
  @SerialName(value = "Version")
  Version,
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Type")
  Type,
  @SerialName(value = "DeployedBy")
  Deployedby,
  @SerialName(value = "DeployedOn")
  Deployedon,
  @SerialName(value = "Status")
  Status,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
