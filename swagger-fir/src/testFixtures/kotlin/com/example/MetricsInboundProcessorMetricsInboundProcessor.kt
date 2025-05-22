package com.example

import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class MetricsInboundProcessorMetricsInboundProcessor(
  /**
   * A set of values that specify the metrics point that is created.
   */
  public val updates: List<PatchTemplate>,
)
