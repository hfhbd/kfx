package com.example.foo

import com.example.bar.Bar
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "Foo",
  namespace = "http://example.com/foo",
)
@JvmInline
public value class Foo private constructor (
  private val value: FooType,
) {
  constructor(bar: Bar, foo: Int) : this(
    FooType(
      bar = bar,
      foo = foo,
    )
  )
  val bar: Bar get() = value.bar
  val foo: Int get() = value.foo
}
