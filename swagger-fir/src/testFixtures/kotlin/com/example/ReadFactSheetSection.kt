package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Define the components that will be available for JUEL expression under the symbol 'lx'.
 */
@Serializable
@SerialName(value = "ReadFactSheetSection")
public data class ReadFactSheetSection(
  /**
   * Specify metrics measurements that can be linked with the processing Fact Sheet
   */
  public val metrics: MetricsMeasurement? = null,
  /**
   * A list of fields that are required to get from Fact Sheet
   */
  public val fields: List<String>,
  /**
   * An object describing what relations and which relation fields to retrieve.
   */
  public val relations: RequiredRelationInfo? = null,
  /**
   * An object describing which tags to retrieve.
   */
  public val tags: TagFilter? = null,
  /**
   * An object describing which subscriptions to retrieve.
   */
  public val subscriptions: SubscriptionFilter? = null,
  /**
   * An object describing which documents to retrieve.
   */
  public val documents: DocumentFilter? = null,
  /**
   * An object describing which impacts to retrieve.
   */
  public val impacts: ImpactFilter? = null,
  /**
   * A flag to determine the fallback response in the function lx.toOrdinal(...). A true (default if absent) makes the value returned by lx.toOrdinal(...) to be zero for non-found cases. False will make the returned value equals NULL.
   */
  public val noNullForOrdinal: Boolean? = null,
  /**
   * An EL expression to describe which factSheet fields from the data model are selected
   */
  public val multipleFields: String? = null,
)
