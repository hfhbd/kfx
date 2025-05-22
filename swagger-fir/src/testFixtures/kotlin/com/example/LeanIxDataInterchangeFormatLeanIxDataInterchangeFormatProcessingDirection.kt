package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class LeanIxDataInterchangeFormatLeanIxDataInterchangeFormatProcessingDirection {
  @SerialName(value = "inbound")
  Inbound,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
