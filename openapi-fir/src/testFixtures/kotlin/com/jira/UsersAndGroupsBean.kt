package com.jira

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UsersAndGroupsBean")
public data class UsersAndGroupsBean(
  public val groups: GroupSuggestionsBean? = null,
  public val users: UserPickerResultsBean? = null,
)
