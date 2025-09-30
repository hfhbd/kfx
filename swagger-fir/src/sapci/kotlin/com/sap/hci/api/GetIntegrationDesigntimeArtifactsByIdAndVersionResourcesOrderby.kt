package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesOrderby {
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Name desc")
  NameDesc,
  @SerialName(value = "ResourceType")
  ResourceType,
  @SerialName(value = "ResourceType desc")
  ResourceTypeDesc,
  @SerialName(value = "Name,ResourceType")
  `Name,ResourceType`,
  @SerialName(value = "Name desc,ResourceType desc")
  `NameDesc,ResourceTypeDesc`,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
