package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesSelect {
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "ResourceType")
  Resourcetype,
  @SerialName(value = "ReferencedResourceType")
  Referencedresourcetype,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
