package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CommentJsonBean")
public data class CommentJsonBean(
  public val author: UserJsonBean? = null,
  public val body: String? = null,
  public val created: String? = null,
  public val id: String? = null,
  public val properties: List<EntityPropertyBean>,
  public val renderedBody: String? = null,
  public val self: String? = null,
  public val updateAuthor: UserJsonBean? = null,
  public val updated: String? = null,
  public val visibility: VisibilityJsonBean? = null,
)
