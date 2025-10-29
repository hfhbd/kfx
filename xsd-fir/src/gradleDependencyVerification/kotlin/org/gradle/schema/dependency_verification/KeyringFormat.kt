package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class KeyringFormat {
  @SerialName(value = "armored")
  Armored,
  @SerialName(value = "binary")
  Binary,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
