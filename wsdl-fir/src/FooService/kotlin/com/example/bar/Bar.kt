package com.example.bar

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

/**
 * Bar details
 */
@Serializable
@XmlSerialName(
  value = "BarType",
  namespace = "http://example.com/bar",
)
public data class Bar(
  /**
   * Valid From
   */
  @XmlElement
  @XmlSerialName(
    value = "ValidFrom",
    namespace = "http://example.com/bar",
  )
  public val validFrom: LocalDate? = null,
)
