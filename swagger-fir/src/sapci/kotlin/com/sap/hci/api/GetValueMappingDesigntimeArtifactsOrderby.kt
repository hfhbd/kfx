package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetValueMappingDesigntimeArtifactsOrderby {
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Name desc")
  NameDesc,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
