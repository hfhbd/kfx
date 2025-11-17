package com.example.foo

import com.example.bar.BarType
import kotlin.Boolean
import kotlin.Int
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "FooType",
  namespace = "http://example.com/foo",
)
public data class FooType(
  @XmlElement
  @XmlSerialName(
    value = "Bar",
    namespace = "http://example.com/bar",
  )
  public val bar: BarType,
  @XmlElement
  @SerialName(value = "Foo")
  public val foo: Int,
  @SerialName(value = "Baz")
  public val baz: Boolean? = null,
)
