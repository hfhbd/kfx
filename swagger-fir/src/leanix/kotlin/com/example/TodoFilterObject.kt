package com.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Query definition to determine the scope, see TodoQueryBody in To-Do Open Api documentation https://app.leanix.net/openapi-explorer?urls.primaryName=To-do
 */
@Serializable
@SerialName(value = "TodoFilterObject")
public data object TodoFilterObject
