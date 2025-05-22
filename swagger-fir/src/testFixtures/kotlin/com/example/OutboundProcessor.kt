package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OutboundProcessor")
public data class OutboundProcessor(
  /**
   * The type that identifies this processor ('outboundFactSheet')
   */
  public val processorType: String,
  /**
   * A not necessarily unique name for this processor
   */
  public val processorName: String? = null,
  /**
   * A description for this processor
   */
  public val processorDescription: String? = null,
  /**
   * Defines to which data object the processor will be applied
   */
  public val filter: FilterConfig? = null,
  /**
   * Whether this processor is executed or not. Default is true.
   */
  public val enabled: Boolean? = null,
  /**
   * A list of facet filters which limit which Fact Sheets are considered for output
   */
  public val scope: Unit? = null,
  /**
   * A list of fields that are required to get from Fact Sheet
   */
  public val fields: List<String>,
  /**
   * An object describing what relations and which relation fields to retrieve.
   */
  public val relations: OutboundRequiredRelationInfo? = null,
  /**
   * An object describing which tags to retrieve.
   */
  public val tags: OutboundTagFilter? = null,
  /**
   * An object describing which subscriptions to retrieve.
   */
  public val subscriptions: OutboundSubscriptionFilter? = null,
  /**
   * An object describing which documents to retrieve.
   */
  public val documents: OutboundDocumentFilter? = null,
  /**
   * Defines where the outbound result LDIF should be uploaded
   */
  public val dataConsumer: DataConsumer? = null,
  /**
   * Specify metrics measurements that can be linked with the processing Fact Sheet
   */
  public val metrics: MetricsMeasurement? = null,
  /**
   * A list of fields that are constructed an exported Fact Sheet
   */
  public val output: List<OutboundFieldTemplate>,
  /**
   * A flag to determine the fallback response in the function lx.toOrdinal(...). A true (default if absent) makes the value returned by lx.toOrdinal(...) to be zero for non-found cases. False will make the returned value equals NULL.
   */
  public val noNullForOrdinal: Boolean? = null,
  /**
   * An EL expression to describe which factSheet fields from the data model are selected
   */
  public val multipleFields: String? = null,
)
