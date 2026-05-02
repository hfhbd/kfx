package com.jira

import kotlinx.serialization.Serializable

@Serializable
public data class UsersAndGroupsBean(
  public val groups: GroupSuggestionsBean? = null,
  public val users: UserPickerResultsBean? = null,
)
