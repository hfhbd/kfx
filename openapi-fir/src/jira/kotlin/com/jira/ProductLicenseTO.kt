package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ProductLicenseTO(
  public val isUnlimitedNumberOfUsers: Boolean? = null,
  public val licenseKey: String? = null,
  public val locale: Locale? = null,
  public val numberOfUsers: Int? = null,
  public val productDisplayName: String? = null,
  public val productKey: String? = null,
)
