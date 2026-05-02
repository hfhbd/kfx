package com.example.foo

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "FaultType",
  namespace = "http://example.com/foo",
)
@Serializable
public data class FaultType(
  @XmlElement
  @XmlSerialName(
    value = "message",
    namespace = "http://example.com/foo",
  )
  public val message: String,
)
