package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class StatusResponseStatus {
  @SerialName(value = "CREATED")
  Created,
  @SerialName(value = "SEQUENTIALIZED")
  Sequentialized,
  @SerialName(value = "PENDING")
  Pending,
  @SerialName(value = "IN_PROGRESS")
  InProgress,
  @SerialName(value = "FINISHED")
  Finished,
  @SerialName(value = "STOPPED")
  Stopped,
  @SerialName(value = "FAILED")
  Failed,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
