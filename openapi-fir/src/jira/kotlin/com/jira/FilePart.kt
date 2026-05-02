package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class FilePart(
  public val contentType: String? = null,
  public val formField: Boolean? = null,
  public val inputStream: Unit? = null,
  public val name: String? = null,
  public val size: Long? = null,
  public val `value`: String? = null,
)
