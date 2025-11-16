package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class FooInputEnum {
  @SerialName(value = "A")
  A,
  @SerialName(value = "B.B")
  BB,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
