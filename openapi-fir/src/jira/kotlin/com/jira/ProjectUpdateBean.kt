package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectUpdateBean")
public data class ProjectUpdateBean(
  public val assigneeType: ProjectUpdateBeanAssigneeType? = null,
  public val avatarId: Long? = null,
  public val categoryId: Long? = null,
  public val description: String? = null,
  public val issueSecurityScheme: Long? = null,
  public val key: String? = null,
  public val lead: String? = null,
  public val name: String? = null,
  public val notificationScheme: Long? = null,
  public val permissionScheme: Long? = null,
  public val projectTemplateKey: String? = null,
  public val projectTypeKey: String? = null,
  public val url: String? = null,
)
