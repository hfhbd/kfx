package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BoardConfigBean")
public data class BoardConfigBean(
  public val columnConfig: ColumnConfigBean? = null,
  public val estimation: EstimationConfigBean? = null,
  public val filter: RelationBean? = null,
  public val id: Long? = null,
  public val name: String? = null,
  public val ranking: RankingConfigBean? = null,
  public val self: String? = null,
  public val subQuery: SubqueryBean? = null,
  public val type: String? = null,
)
