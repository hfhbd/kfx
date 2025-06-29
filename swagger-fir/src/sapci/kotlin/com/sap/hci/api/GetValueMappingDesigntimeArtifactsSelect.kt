package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetValueMappingDesigntimeArtifactsSelect {
  @SerialName(value = "Id")
  Id,
  @SerialName(value = "Version")
  Version,
  @SerialName(value = "PackageId")
  Packageid,
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Description")
  Description,
  @SerialName(value = "ArtifactContent")
  Artifactcontent,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
