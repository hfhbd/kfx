package com.example.bar

import kotlin.jvm.JvmInline
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

/**
 * Bar details
 */
@Serializable
@XmlSerialName(
  value = "Bar",
  namespace = "http://example.com/bar",
)
@JvmInline
public value class Bar private constructor(
  private val _value: BarType,
) {
  public constructor(validFrom: LocalDate? = null) : this(BarType(validFrom))

  /**
   * Valid From
   */
  public val validFrom: LocalDate? get() = _value.validFrom
}
