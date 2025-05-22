package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UserAnonymizationValidationBean")
public data class UserAnonymizationValidationBean(
  public val affectedEntities: Unit? = null,
  public val businessLogicValidationFailed: Boolean? = null,
  public val deleted: Boolean? = null,
  public val displayName: String? = null,
  public val email: String? = null,
  public val errors: Unit? = null,
  public val expand: String? = null,
  public val operations: List<String>,
  public val success: Boolean? = null,
  public val userKey: String? = null,
  public val userName: String? = null,
  public val warnings: Unit? = null,
)
