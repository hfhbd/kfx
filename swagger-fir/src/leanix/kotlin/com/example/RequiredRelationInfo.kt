package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RequiredRelationInfo")
public data class RequiredRelationInfo(
  /**
   * The relation types to filter for
   */
  public val filter: List<String>,
  /**
   * The names of the fields of a relation that are be available
   */
  public val fields: List<String>,
  /**
   * The names of the fields of the target Fact Sheet that are available
   */
  public val targetFields: List<String>,
  /**
   * Whether to get constraining relations of relations
   */
  public val constrainingRelations: Boolean? = null,
  /**
   * An EL expression to describe which relation fields from the data model are selected
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val multipleFields: String? = null,
  /**
   * An EL expression to describe which relation type to filter for
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val multipleFilters: String? = null,
  /**
   * An EL expression to describe which factSheet fields on the target factSheet are selected
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val multipleTargetFields: String? = null,
)
