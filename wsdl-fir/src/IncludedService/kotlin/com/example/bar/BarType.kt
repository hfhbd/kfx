package com.example.bar

import kotlin.Long
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

/**
 * Bar details
 */
@XmlSerialName(
  value = "BarType",
  namespace = "http://example.com/bar",
)
@Serializable
@SerialName(value = "BarType")
public data class BarType(
  /**
   * Valid From
   */
  @XmlElement
  @XmlSerialName(
    value = "ValidFrom",
    namespace = "http://example.com/bar",
  )
  @SerialName(value = "ValidFrom")
  public val validFrom: LocalDate? = null,
  @SerialName(value = "Baz")
  public val baz: Long? = null,
)
