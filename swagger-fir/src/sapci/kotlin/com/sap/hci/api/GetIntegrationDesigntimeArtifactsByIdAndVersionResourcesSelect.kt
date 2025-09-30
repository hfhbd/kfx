package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesSelect {
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "ResourceType")
  ResourceType,
  @SerialName(value = "ReferencedResourceType")
  ReferencedResourceType,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
