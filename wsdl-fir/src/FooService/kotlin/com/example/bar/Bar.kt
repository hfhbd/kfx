package com.example.bar

import kotlin.jvm.JvmInline
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

/**
 * Bar details
 */
@JvmInline
@Serializable
@XmlSerialName(
  value = "Bar",
  namespace = "http://example.com/bar",
)
public value class Bar private constructor(
  private val _value: BarType,
) {
  /**
   * Valid From
   */
  public val validFrom: LocalDate?
    get() = _value.validFrom

  public constructor(validFrom: LocalDate? = null) : this(BarType(validFrom))
}
