package com.example

import kotlin.String
import kotlinx.serialization.Serializable

/**
 * Upload the outbound result LDFI to the target Azure storage account and container
 */
@Serializable
public data class AzureStorageDataConsumer(
  /**
   * The connection string to the target Azure storage account.
   */
  public val connectionString: String,
  /**
   * The container name to which the outbound result should be uploaded to.
   */
  public val containerName: String,
) : DataConsumer
