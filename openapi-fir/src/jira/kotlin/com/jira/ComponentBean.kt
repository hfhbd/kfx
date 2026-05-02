package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ComponentBean")
public data class ComponentBean(
  public val archived: Boolean? = null,
  public val assigneeType: ComponentBeanAssigneeType? = null,
  public val deleted: Boolean? = null,
  public val description: String? = null,
  public val id: String? = null,
  public val lead: UserBean? = null,
  public val leadUserName: String? = null,
  public val name: String? = null,
  public val project: String? = null,
  public val self: String? = null,
)
