package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProductLicenseTO")
public data class ProductLicenseTO(
  public val isUnlimitedNumberOfUsers: Boolean? = null,
  public val licenseKey: String? = null,
  public val locale: Locale? = null,
  public val numberOfUsers: Int? = null,
  public val productDisplayName: String? = null,
  public val productKey: String? = null,
)
