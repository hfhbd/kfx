package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsSelect {
  @SerialName(value = "ParameterKey")
  Parameterkey,
  @SerialName(value = "ParameterValue")
  Parametervalue,
  @SerialName(value = "DataType")
  Datatype,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
