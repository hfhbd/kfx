package com.example.foo

import com.example.bar.BarType
import kotlin.Boolean
import kotlin.Int
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "FooType",
  namespace = "http://example.com/foo",
)
@Serializable
public data class FooType(
  @XmlElement
  @XmlSerialName(
    value = "Bar",
    namespace = "http://example.com/foo",
  )
  @SerialName(value = "Bar")
  public val bar: BarType,
  @XmlElement
  @XmlSerialName(
    value = "Foo",
    namespace = "http://example.com/foo",
  )
  @SerialName(value = "Foo")
  public val foo: Int,
  @XmlElement
  @XmlSerialName(
    value = "ListEntries",
    namespace = "http://example.com/foo",
  )
  @SerialName(value = "ListEntries")
  public val listEntries: ListEntryType,
  @XmlElement
  @XmlSerialName(
    value = "ListEntriesWithOtherElements",
    namespace = "http://example.com/foo",
  )
  @SerialName(value = "ListEntriesWithOtherElements")
  public val listEntriesWithOtherElements: ListEntryWithOtherElementsType,
  @XmlElement
  @XmlSerialName(
    value = "ListRefs",
    namespace = "http://example.com/foo",
  )
  @SerialName(value = "ListRefs")
  public val listRefs: ListRefType,
  @SerialName(value = "Baz")
  public val baz: Boolean? = null,
)
