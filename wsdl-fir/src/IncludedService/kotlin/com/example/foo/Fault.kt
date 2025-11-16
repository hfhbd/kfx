package com.example.foo

import kotlin.String
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@JvmInline
@Serializable
@XmlSerialName(
  value = "Fault",
  namespace = "http://example.com/foo",
)
public value class Fault private constructor(
  private val _value: FaultType,
) {
  public val message: String
    get() = _value.message

  public constructor(message: String) : this(FaultType(message))
}
