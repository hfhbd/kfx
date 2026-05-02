package com.example.foo

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "SimpleType",
  namespace = "http://example.com/foo",
)
@Serializable
public data class SimpleType(
  @XmlElement
  @XmlSerialName(
    value = "someString",
    namespace = "http://example.com/foo",
  )
  public val someString: String,
)
