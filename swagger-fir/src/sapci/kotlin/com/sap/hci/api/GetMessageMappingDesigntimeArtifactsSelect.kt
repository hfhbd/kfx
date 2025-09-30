package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetMessageMappingDesigntimeArtifactsSelect {
  @SerialName(value = "Id")
  Id,
  @SerialName(value = "Version")
  Version,
  @SerialName(value = "PackageId")
  PackageId,
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Description")
  Description,
  @SerialName(value = "ArtifactContent")
  ArtifactContent,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
