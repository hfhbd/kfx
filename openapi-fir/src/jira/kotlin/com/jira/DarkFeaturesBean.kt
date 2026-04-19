package com.jira

import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DarkFeaturesBean")
public data class DarkFeaturesBean(
  public val siteFeatures: Map<String, DarkFeaturePropertyBean> = emptyMap(),
  public val systemFeatures: Map<String, DarkFeaturePropertyBean> = emptyMap(),
)
