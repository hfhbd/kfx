package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class CreateIntegrationPackagesOverwrite {
  @SerialName(value = "true")
  True,
  @SerialName(value = "false")
  False,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
