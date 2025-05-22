package odata.error

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Main")
public data class Main(
  public val code: String,
  public val message: MainMessage,
)
