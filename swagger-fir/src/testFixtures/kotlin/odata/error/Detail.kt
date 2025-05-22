package odata.error

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Detail")
public data class Detail(
  public val code: String,
  public val message: String,
  public val target: String? = null,
)
