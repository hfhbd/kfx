package com.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Upload the outbound result LDFI to the default cloud storage provided by LeanIX
 */
@Serializable
@SerialName(value = "LeanIxStorageDataConsumer")
public data object LeanIxStorageDataConsumer : DataConsumer
