package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetValueMappingDesigntimeArtifacts(Id='{Id}',Version='{Version}')ValMapSchema")
public data class GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchema(
  public val `value`: ValMapSchema,
)
