package com.example.foo

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "Fault",
  namespace = "http://example.com/foo",
)
@JvmInline
public value class Fault private constructor(
  private val value: FaultType,
) {
  constructor(message: String) : this(FaultType(message))

  val message: String get() = value.message
}
