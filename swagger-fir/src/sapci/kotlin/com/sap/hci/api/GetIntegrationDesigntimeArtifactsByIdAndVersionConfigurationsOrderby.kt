package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsOrderby {
  @SerialName(value = "ParameterKey")
  ParameterKey,
  @SerialName(value = "ParameterKey desc")
  ParameterKeyDesc,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
