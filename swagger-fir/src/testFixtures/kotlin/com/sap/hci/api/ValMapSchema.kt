package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ValMapSchema")
public data class ValMapSchema(
  public val SrcAgency: String? = null,
  public val SrcId: String? = null,
  public val TgtAgency: String? = null,
  public val TgtId: String? = null,
  public val State: String? = null,
  public val DefaultValMaps: ValMapSchemaDefaultValMaps? = null,
  public val ValMaps: ValMapSchemaValMaps? = null,
)
