package com.example

import kotlinx.serialization.Serializable

/**
 * Upload the outbound result LDFI to the default cloud storage provided by LeanIX
 */
@Serializable
public data object LeanIxStorageDataConsumer : DataConsumer
