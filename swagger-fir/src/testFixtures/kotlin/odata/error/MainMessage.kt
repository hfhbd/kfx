package odata.error

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "MainMessage")
public data class MainMessage(
  public val lang: String,
  public val `value`: String,
)
