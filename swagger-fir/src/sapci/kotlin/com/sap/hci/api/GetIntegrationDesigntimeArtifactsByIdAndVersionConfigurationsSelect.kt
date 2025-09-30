package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsSelect {
  @SerialName(value = "ParameterKey")
  ParameterKey,
  @SerialName(value = "ParameterValue")
  ParameterValue,
  @SerialName(value = "DataType")
  DataType,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
