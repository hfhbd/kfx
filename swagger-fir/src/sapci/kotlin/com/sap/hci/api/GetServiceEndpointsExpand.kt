package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetServiceEndpointsExpand {
  @SerialName(value = "EntryPoints")
  EntryPoints,
  @SerialName(value = "ApiDefinitions")
  ApiDefinitions,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
