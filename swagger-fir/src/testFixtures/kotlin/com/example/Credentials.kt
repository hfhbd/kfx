package com.example

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Credential settting for synchronization run
 */
@Serializable
@SerialName(value = "Credentials")
public data class Credentials(
  /**
   * The API token that is used for executing the synchronization run
   */
  public val apiToken: String? = null,
  /**
   * Whether to use the technical user of the workspace to execute the synchronization run. If true, 'apiToken' field will be ignored.
   */
  public val useTechnicalUser: Boolean? = null,
  /**
   * The ID of the technical user that is used for executing the synchronization run.
   */
  public val technicalUserId: String? = null,
)
