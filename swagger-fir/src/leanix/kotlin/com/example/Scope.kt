package com.example

import kotlinx.serialization.Serializable

/**
 * The criteria for selection of entities. In case you use facetFilters inside, it is possible to use Juel expressions inside 'keys', for example: ${customFields.factSheetType}
 */
@Serializable
public data object Scope
