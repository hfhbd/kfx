package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Warning")
public data class Warning(
  /**
   * The error message
   */
  public val processor: Processor? = null,
  /**
   * The content ID element from the LDIF that was processed when this warning was generated.
   */
  public val content: String? = null,
  /**
   * The error message
   */
  public val message: String? = null,
  /**
   * Additional information to complement the error message
   */
  public val detail: String? = null,
  /**
   * An internal category that identifies of processing category where the warning was originated.
   */
  public val category: String? = null,
  /**
   * An identifier of the type of alert. Possible values are WARNING, ERROR
   */
  public val status: String? = null,
)
