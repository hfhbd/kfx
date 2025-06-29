package com.jira

import kotlin.String
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "LicenseValidationResults")
public data class LicenseValidationResults(
  public val errors: Map<String, String>? = null,
  public val licenseString: String? = null,
)
