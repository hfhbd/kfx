package dev.central

import kotlin.Double
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class DeployedFileDTO(
  public val relativePath: String? = null,
  public val fileName: String? = null,
  public val fileSize: Double? = null,
  public val fileTimestamp: Double? = null,
)
