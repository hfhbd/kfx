package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionSelect {
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Version")
  Version,
  @SerialName(value = "PackageId")
  Packageid,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
