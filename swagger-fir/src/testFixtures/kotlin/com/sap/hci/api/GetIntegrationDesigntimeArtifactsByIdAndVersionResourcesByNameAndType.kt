package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationDesigntimeArtifacts(Id='{Id}',Version='{Version}')Resources(Name='{Name}',ResourceType='{Type}')")
public data class GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndType(
  public val d: Resource? = null,
)
