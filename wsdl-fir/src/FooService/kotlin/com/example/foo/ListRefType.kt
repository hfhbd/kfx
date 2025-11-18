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
@Serializable
@XmlSerialName(
  value = "ListRefType",
  namespace = "http://example.com/foo",
)
public data class ListRefType(
  @XmlElement
  @XmlSerialName(
    value = "Bar",
    namespace = "http://example.com/bar",
  )
  public val bar: List<BarType> = emptyList(),
  @SerialName(value = "Size")
  public val size: Long? = null,
)
