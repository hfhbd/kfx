package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationRuntimeArtifactsOrderby {
  @SerialName(value = "Id")
  Id,
  @SerialName(value = "Id desc")
  IdDesc,
  @SerialName(value = "Version")
  Version,
  @SerialName(value = "Version desc")
  VersionDesc,
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Name desc")
  NameDesc,
  @SerialName(value = "Type")
  Type,
  @SerialName(value = "Type desc")
  TypeDesc,
  @SerialName(value = "DeployedBy")
  DeployedBy,
  @SerialName(value = "DeployedBy desc")
  DeployedByDesc,
  @SerialName(value = "DeployedOn")
  DeployedOn,
  @SerialName(value = "DeployedOn desc")
  DeployedOnDesc,
  @SerialName(value = "Status")
  Status,
  @SerialName(value = "Status desc")
  StatusDesc,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
