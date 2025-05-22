package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class PutIntegrationDesigntimeArtifactsByIdAndVersionLinksResourcesByNameAndTypeReferencedResourceType {
  @SerialName(value = "wsdl")
  Wsdl,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
