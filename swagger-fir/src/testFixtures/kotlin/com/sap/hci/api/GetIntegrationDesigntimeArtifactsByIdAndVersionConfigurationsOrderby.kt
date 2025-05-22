package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsOrderby {
  @SerialName(value = "ParameterKey")
  Parameterkey,
  @SerialName(value = "ParameterKey desc")
  `Parameterkey desc`,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
