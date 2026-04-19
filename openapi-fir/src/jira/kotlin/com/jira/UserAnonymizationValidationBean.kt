package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UserAnonymizationValidationBean")
public data class UserAnonymizationValidationBean(
  public val affectedEntities: Map<String, List<AffectedEntityBean>> = emptyMap(),
  public val businessLogicValidationFailed: Boolean? = null,
  public val deleted: Boolean? = null,
  public val displayName: String? = null,
  public val email: String? = null,
  public val errors: Map<String, ErrorCollection> = emptyMap(),
  public val expand: String? = null,
  public val operations: List<String> = emptyList(),
  public val success: Boolean? = null,
  public val userKey: String? = null,
  public val userName: String? = null,
  public val warnings: Map<String, ErrorCollection> = emptyMap(),
)
