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
  value = "ListEntryWithOtherElementsType",
  namespace = "http://example.com/foo",
)
@Serializable
@SerialName(value = "ListEntryWithOtherElementsType")
public data class ListEntryWithOtherElementsType(
  @XmlElement
  @XmlSerialName(
    value = "Bars",
    namespace = "http://example.com/foo",
  )
  @SerialName(value = "Bars")
  public val bars: List<BarType> = emptyList(),
  @XmlElement
  @XmlSerialName(
    value = "Bazs",
    namespace = "http://example.com/foo",
  )
  @SerialName(value = "Bazs")
  public val bazs: List<BarType> = emptyList(),
  @XmlElement
  @XmlSerialName(
    value = "SimpleTypes",
    namespace = "http://example.com/foo",
  )
  @SerialName(value = "SimpleTypes")
  public val simpleTypes: List<SimpleType> = emptyList(),
  @SerialName(value = "Size")
  public val size: Long? = null,
)
