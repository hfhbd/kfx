package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class AffectedEntityBeanType {
  @SerialName(value = "ANONYMIZE")
  Anonymize,
  @SerialName(value = "TRANSFER_OWNERSHIP")
  TransferOwnership,
  @SerialName(value = "REMOVE")
  Remove,
  @SerialName(value = "MANUAL")
  Manual,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
