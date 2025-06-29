package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ServiceEndpoint")
public data class ServiceEndpoint(
  public val Name: String? = null,
  public val Id: String? = null,
  public val Title: String? = null,
  public val Version: String? = null,
  public val Summary: String? = null,
  public val Description: String? = null,
  public val LastUpdated: String? = null,
  public val Protocol: String? = null,
  public val EntryPoints: EntryPoint,
  public val ApiDefinitions: Definition,
)
