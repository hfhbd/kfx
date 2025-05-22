package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AvatarCroppingBean")
public data class AvatarCroppingBean(
  public val cropperOffsetX: Int? = null,
  public val cropperOffsetY: Int? = null,
  public val cropperWidth: Int? = null,
  public val needsCropping: Boolean? = null,
  public val url: String? = null,
)
