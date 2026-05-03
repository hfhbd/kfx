package com.example.foo

import com.example.bar.BarType
import kotlin.Long
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

/**
 * ListEntryType details
 */
@XmlSerialName(
  value = "ListRefType",
  namespace = "http://example.com/foo",
)
@Serializable
@SerialName(value = "ListRefType")
public data class ListRefType(
  @XmlElement
  @XmlSerialName(
    value = "Bar",
    namespace = "http://example.com/bar",
  )
  @SerialName(value = "Bar")
  public val bar: List<BarType> = emptyList(),
  @SerialName(value = "Size")
  public val size: Long? = null,
)
