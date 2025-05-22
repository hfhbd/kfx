package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class OutboundInputOutboundInputProcessingMode {
  @SerialName(value = "partial")
  Partial,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
