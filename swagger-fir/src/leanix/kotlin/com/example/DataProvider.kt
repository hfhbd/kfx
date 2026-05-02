package com.example

import kotlin.String
import kotlinx.serialization.Serializable

/**
 * Definition of the provider which provides the inbound LDIF
 */
@Serializable
public data class DataProvider(
  /**
   * The url from which the inbound LDIF should be downloaded from
   */
  public val url: String? = null,
  /**
   * The type of storage provider. Use 'azureStorage' to optimize for Azure Storage, or null for other types.
   */
  public val type: String? = null,
)
