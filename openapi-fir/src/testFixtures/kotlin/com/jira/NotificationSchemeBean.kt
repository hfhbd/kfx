package com.jira

import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "NotificationSchemeBean")
public data class NotificationSchemeBean(
  public val description: String? = null,
  public val expand: String? = null,
  public val id: Long? = null,
  public val name: String? = null,
  public val notificationSchemeEvents: Unit? = null,
  public val self: String? = null,
)
