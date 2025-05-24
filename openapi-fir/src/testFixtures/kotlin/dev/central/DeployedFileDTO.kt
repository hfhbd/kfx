package dev.central

import kotlin.Double
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DeployedFileDTO")
public data class DeployedFileDTO(
  public val relativePath: String? = null,
  public val fileName: String? = null,
  public val fileSize: Double? = null,
  public val fileTimestamp: Double? = null,
)
