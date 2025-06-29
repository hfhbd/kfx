package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetIntegrationPackagesByIdCustomTagsOrderby {
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Name desc")
  `Name desc`,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
