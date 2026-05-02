package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GroupLabelBean")
public data class GroupLabelBean(
  public val text: String? = null,
  public val title: String? = null,
  public val type: GroupLabelBeanType? = null,
)
