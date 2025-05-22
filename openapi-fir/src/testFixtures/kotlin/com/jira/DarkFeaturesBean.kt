package com.jira

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DarkFeaturesBean")
public data class DarkFeaturesBean(
  public val siteFeatures: Unit? = null,
  public val systemFeatures: Unit? = null,
)
