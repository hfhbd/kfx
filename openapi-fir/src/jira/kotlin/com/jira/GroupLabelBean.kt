package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class GroupLabelBean(
  public val text: String? = null,
  public val title: String? = null,
  public val type: GroupLabelBeanType? = null,
)
