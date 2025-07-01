package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetServiceEndpointsExpand {
  @SerialName(value = "EntryPoints")
  Entrypoints,
  @SerialName(value = "ApiDefinitions")
  Apidefinitions,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
