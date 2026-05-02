package com.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The criteria for selection of entities. In case you use facetFilters inside, it is possible to use Juel expressions inside 'keys', for example: ${customFields.factSheetType}
 */
@Serializable
@SerialName(value = "Scope")
public data object Scope
