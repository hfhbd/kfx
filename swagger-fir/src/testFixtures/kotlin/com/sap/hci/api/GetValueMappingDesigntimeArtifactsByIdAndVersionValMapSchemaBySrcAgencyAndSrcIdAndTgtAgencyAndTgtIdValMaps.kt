package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetValueMappingDesigntimeArtifacts(Id='{Id}',Version='{Version}')ValMapSchema(SrcAgency='{SrcAgency}',SrcId='{SrcId}',TgtAgency='{TgtAgency}',TgtId='{TgtId}')ValMaps")
public data class GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdValMaps(
  public val `value`: ValMaps,
)
