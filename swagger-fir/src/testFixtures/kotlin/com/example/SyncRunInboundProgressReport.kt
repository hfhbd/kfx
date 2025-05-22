package com.example

import kotlin.Int
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SyncRunInboundProgressReport")
public data class SyncRunInboundProgressReport(
  /**
   * Number of elements processed from content in the current run level for Inbound processing.
   */
  public val processedContentCount: Int? = null,
  /**
   * Number of errors in the current run level, valid for Inbound
   */
  public val errorCount: Int? = null,
  /**
   * Number of GraphQL request made to Pathfinder backend in the current run level, not applicable for Outbound
   */
  public val graphQLRequestCount: Int? = null,
  /**
   * Identifier of the run level being executed, valid only for Inbound processing.
   */
  public val runLevelIndex: Int? = null,
  /**
   * Status of the synchronization Job
   */
  public val status: Int? = null,
  /**
   * Number of elements build in the output content. Valid for Outbound
   */
  public val contentsCount: Int? = null,
  /**
   * Number of warnings detected during Outbound processing
   */
  public val warningsCount: Int? = null,
  /**
   * Number of elements found in the Outbound Scope or the Scope of the processors
   */
  public val itemsInScopeCount: Int? = null,
)
