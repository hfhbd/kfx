package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class CreateCopyIntegrationPackageImportMode {
  @SerialName(value = "'CREATE_COPY'")
  `'createCopy'`,
  @SerialName(value = "'OVERWRITE'")
  `'overwrite'`,
  @SerialName(value = "'OVERWRITE_MERGE'")
  `'overwriteMerge'`,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
