package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class NodeBeanState {
  @SerialName(value = "ACTIVE")
  Active,
  @SerialName(value = "PASSIVE")
  Passive,
  @SerialName(value = "ACTIVATING")
  Activating,
  @SerialName(value = "PASSIVATING")
  Passivating,
  @SerialName(value = "OFFLINE")
  Offline,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
