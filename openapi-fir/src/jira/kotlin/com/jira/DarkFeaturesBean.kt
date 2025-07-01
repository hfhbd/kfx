package com.jira

import kotlin.String
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DarkFeaturesBean")
public data class DarkFeaturesBean(
  public val siteFeatures: Map<String, DarkFeaturePropertyBean>? = null,
  public val systemFeatures: Map<String, DarkFeaturePropertyBean>? = null,
)
