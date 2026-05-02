package com.example.foo

import kotlin.String
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@JvmInline
@XmlSerialName(
  value = "Fault",
  namespace = "http://example.com/foo",
)
@Serializable
public value class Fault private constructor(
  private val _value: FaultType,
) {
  public val message: String
    get() = _value.message

  public constructor(message: String) : this(FaultType(message))
}
