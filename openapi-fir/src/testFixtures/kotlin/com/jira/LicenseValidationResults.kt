package com.jira

import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "LicenseValidationResults")
public data class LicenseValidationResults(
  public val errors: Unit? = null,
  public val licenseString: String? = null,
)
