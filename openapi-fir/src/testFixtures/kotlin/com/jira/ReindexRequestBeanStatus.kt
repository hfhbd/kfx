package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ReindexRequestBeanStatus {
  @SerialName(value = "PENDING")
  Pending,
  @SerialName(value = "ACTIVE")
  Active,
  @SerialName(value = "RUNNING")
  Running,
  @SerialName(value = "FAILED")
  Failed,
  @SerialName(value = "COMPLETE")
  Complete,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
