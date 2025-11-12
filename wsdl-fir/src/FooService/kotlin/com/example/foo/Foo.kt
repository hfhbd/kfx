package com.example.foo

import com.example.bar.Bar
import kotlin.Int
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "FooType",
  namespace = "http://example.com/foo",
)
public data class Foo(
  @XmlElement
  @XmlSerialName(
    value = "Bar",
    namespace = "http://example.com/foo",
  )
  public val bar: Bar,
  @XmlElement
  @XmlSerialName(
    value = "Foo",
    namespace = "http://example.com/foo",
  )
  public val foo: Int,
)
