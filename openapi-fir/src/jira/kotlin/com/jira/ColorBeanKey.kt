package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ColorBeanKey {
  @SerialName(value = "color_1")
  Color1,
  @SerialName(value = "color_2")
  Color2,
  @SerialName(value = "color_3")
  Color3,
  @SerialName(value = "color_4")
  Color4,
  @SerialName(value = "color_5")
  Color5,
  @SerialName(value = "color_6")
  Color6,
  @SerialName(value = "color_7")
  Color7,
  @SerialName(value = "color_8")
  Color8,
  @SerialName(value = "color_9")
  Color9,
  @SerialName(value = "color_10")
  Color10,
  @SerialName(value = "color_11")
  Color11,
  @SerialName(value = "color_12")
  Color12,
  @SerialName(value = "color_13")
  Color13,
  @SerialName(value = "color_14")
  Color14,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
