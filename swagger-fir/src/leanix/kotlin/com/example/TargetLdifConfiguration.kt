package com.example

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Configuration for the created LDIF, in case 'writeToLdif' is used
 */
@Serializable
@SerialName(value = "TargetLdifConfiguration")
public data class TargetLdifConfiguration(
  /**
   * A list of key-value pairs that evaluates to be the keys of the new LDIF
   */
  public val ldifKeys: List<KeyValueTemplate>,
  /**
   * Define the target location to which the created LDIF should be uploaded to
   */
  public val dataConsumer: DataConsumer? = null,
)
