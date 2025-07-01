package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetServiceEndpointsInlinecount {
  @SerialName(value = "allpages")
  Allpages,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
