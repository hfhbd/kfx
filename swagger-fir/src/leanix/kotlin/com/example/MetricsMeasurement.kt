package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Specify multiple measurements that are fetched along with the processing Fact Sheet, it is possible to use juel expression in any of the values
 */
@Serializable
@SerialName(value = "MetricsMeasurement")
public data class MetricsMeasurement(
  /**
   * The name to be used for this measurement in the context of 'lx.metrics.<name>'.
   */
  public val name: String? = null,
  /**
   * The name of the measurement as it is known in the workspace.
   */
  public val measurement: String? = null,
  /**
   * The name of the field that belongs to the measurement where the data will be collected.
   */
  public val fieldName: String? = null,
  /**
   * Optional, grouping criteria, it is a time duration value, e.g. '1d'.
   */
  public val groupBy: String? = null,
  /**
   * Optional, start of the period of time to fetch data. If 'start' is defined, then at least 'end' or 'duration' must be also defined
   */
  public val start: String? = null,
  /**
   * Optional, end of the period of time to fetch data, must be defined based on the 'start' field.
   */
  public val end: String? = null,
  /**
   * Optional, the duration expression (time interval) to express the period of time to fetch data. ISO-8601 duration format PnDTnHnMn is expected
   */
  public val duration: String? = null,
  /**
   * The rules that apply to filter data in the context of the Fact Sheet being processed.
   */
  public val rules: MetricRules? = null,
)
