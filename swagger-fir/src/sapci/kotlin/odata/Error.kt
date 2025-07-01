package odata

import kotlin.Exception
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import odata.error.Main

@Serializable
@SerialName(value = "Error")
public data class Error(
  public val error: Main,
) : Exception()
