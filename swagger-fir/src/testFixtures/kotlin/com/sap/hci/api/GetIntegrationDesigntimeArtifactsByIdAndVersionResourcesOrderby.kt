package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesOrderby {
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Name desc")
  `Name desc`,
  @SerialName(value = "ResourceType")
  Resourcetype,
  @SerialName(value = "ResourceType desc")
  `Resourcetype desc`,
  @SerialName(value = "Name,ResourceType")
  `Name,resourcetype`,
  @SerialName(value = "Name desc,ResourceType desc")
  `Name desc,resourcetype desc`,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
