package com.example.bar

import kotlin.jvm.JvmInline
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@JvmInline
@XmlSerialName(
  value = "Empty",
  namespace = "http://example.com/bar",
)
@Serializable
@SerialName(value = "Empty")
public value class Empty(
  public val _value: EmptyType,
) {
  public constructor() : this(EmptyType)
}
