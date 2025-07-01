package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsFormat {
  @SerialName(value = "json")
  Json,
  @SerialName(value = "xml")
  Xml,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
