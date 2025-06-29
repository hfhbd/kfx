package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class InboundProcessorConfigurationProcessingMode {
  @SerialName(value = "partial")
  Partial,
  @SerialName(value = "full")
  Full,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
