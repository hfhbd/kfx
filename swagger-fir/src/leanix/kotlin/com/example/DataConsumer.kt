package com.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Define the target location to which the outbound result LDIF should be uploaded to
 */
@Serializable
@SerialName(value = "DataConsumer")
public sealed interface DataConsumer
