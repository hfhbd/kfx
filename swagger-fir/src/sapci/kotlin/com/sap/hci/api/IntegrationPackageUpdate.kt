package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IntegrationPackage-update")
public data class IntegrationPackageUpdate(
  public val Name: String? = null,
  public val Description: String? = null,
  public val ShortText: String? = null,
  public val Version: String? = null,
  public val Vendor: String? = null,
  public val Mode: String? = null,
  public val SupportedPlatform: String? = null,
  public val Products: String? = null,
  public val Keywords: String? = null,
  public val Countries: String? = null,
  public val Industries: String? = null,
  public val LineOfBusiness: String? = null,
)
