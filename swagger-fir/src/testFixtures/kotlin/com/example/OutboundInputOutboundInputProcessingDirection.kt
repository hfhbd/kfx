package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class OutboundInputOutboundInputProcessingDirection {
  @SerialName(value = "outbound")
  Outbound,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
