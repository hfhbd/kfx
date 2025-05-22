package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ColorBeanKey {
  @SerialName(value = "color_1")
  Color_1,
  @SerialName(value = "color_2")
  Color_2,
  @SerialName(value = "color_3")
  Color_3,
  @SerialName(value = "color_4")
  Color_4,
  @SerialName(value = "color_5")
  Color_5,
  @SerialName(value = "color_6")
  Color_6,
  @SerialName(value = "color_7")
  Color_7,
  @SerialName(value = "color_8")
  Color_8,
  @SerialName(value = "color_9")
  Color_9,
  @SerialName(value = "color_10")
  Color_10,
  @SerialName(value = "color_11")
  Color_11,
  @SerialName(value = "color_12")
  Color_12,
  @SerialName(value = "color_13")
  Color_13,
  @SerialName(value = "color_14")
  Color_14,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
