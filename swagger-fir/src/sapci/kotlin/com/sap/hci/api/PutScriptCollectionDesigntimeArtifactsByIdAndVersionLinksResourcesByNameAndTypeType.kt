package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class PutScriptCollectionDesigntimeArtifactsByIdAndVersionLinksResourcesByNameAndTypeType {
  @SerialName(value = "edmx")
  Edmx,
  @SerialName(value = "groovy")
  Groovy,
  @SerialName(value = "jar")
  Jar,
  @SerialName(value = "js")
  Js,
  @SerialName(value = "mmap")
  Mmap,
  @SerialName(value = "opmap")
  Opmap,
  @SerialName(value = "wsdl")
  Wsdl,
  @SerialName(value = "xsd")
  Xsd,
  @SerialName(value = "xslt")
  Xslt,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
