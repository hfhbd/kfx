package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "NotificationJsonBean")
public data class NotificationJsonBean(
  public val htmlBody: String? = null,
  public val restrict: RestrictJsonBean? = null,
  public val subject: String? = null,
  public val textBody: String? = null,
  public val to: ToJsonBean? = null,
)
