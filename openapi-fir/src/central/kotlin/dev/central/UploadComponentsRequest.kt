package dev.central

import kotlin.ByteArray
import kotlinx.serialization.Serializable

@Serializable
public data class UploadComponentsRequest(
  public val bundle: ByteArray? = null,
)
