package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ReindexRequestBeanType {
  @SerialName(value = "IMMEDIATE")
  Immediate,
  @SerialName(value = "DELAYED")
  Delayed,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
