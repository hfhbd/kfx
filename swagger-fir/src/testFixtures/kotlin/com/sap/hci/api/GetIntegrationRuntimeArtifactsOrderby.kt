package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationRuntimeArtifactsOrderby {
  @SerialName(value = "Id")
  Id,
  @SerialName(value = "Id desc")
  `Id desc`,
  @SerialName(value = "Version")
  Version,
  @SerialName(value = "Version desc")
  `Version desc`,
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Name desc")
  `Name desc`,
  @SerialName(value = "Type")
  Type,
  @SerialName(value = "Type desc")
  `Type desc`,
  @SerialName(value = "DeployedBy")
  Deployedby,
  @SerialName(value = "DeployedBy desc")
  `Deployedby desc`,
  @SerialName(value = "DeployedOn")
  Deployedon,
  @SerialName(value = "DeployedOn desc")
  `Deployedon desc`,
  @SerialName(value = "Status")
  Status,
  @SerialName(value = "Status desc")
  `Status desc`,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
