package dev.central

import kotlin.ByteArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UploadComponentsRequest")
public data class UploadComponentsRequest(
  public val bundle: ByteArray? = null,
)
