package com.example.foo

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "FaultType",
  namespace = "http://example.com/foo",
)
public data class FaultType(
  @XmlElement
  public val message: String,
)
