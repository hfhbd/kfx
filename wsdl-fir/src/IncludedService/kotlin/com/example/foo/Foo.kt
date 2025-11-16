package com.example.foo

import com.example.bar.BarType
import kotlin.Int
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@JvmInline
@Serializable
@XmlSerialName(
  value = "Foo",
  namespace = "http://example.com/foo",
)
public value class Foo private constructor(
  private val _value: FooType,
) {
  public val bar: BarType
    get() = _value.bar

  public val foo: Int
    get() = _value.foo

  public constructor(bar: BarType, foo: Int) : this(FooType(bar, foo))
}
