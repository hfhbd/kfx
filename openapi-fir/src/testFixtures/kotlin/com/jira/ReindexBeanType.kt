package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ReindexBeanType {
  @SerialName(value = "FOREGROUND")
  Foreground,
  @SerialName(value = "BACKGROUND")
  Background,
  @SerialName(value = "BACKGROUND_PREFFERED")
  BackgroundPreffered,
  @SerialName(value = "BACKGROUND_PREFERRED")
  BackgroundPreferred,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
