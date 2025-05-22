package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GetServiceEndpointsSelect {
  @SerialName(value = "Name")
  Name,
  @SerialName(value = "Id")
  Id,
  @SerialName(value = "Title")
  Title,
  @SerialName(value = "Version")
  Version,
  @SerialName(value = "Summary")
  Summary,
  @SerialName(value = "Description")
  Description,
  @SerialName(value = "LastUpdated")
  Lastupdated,
  @SerialName(value = "Protocol")
  Protocol,
  @SerialName(value = "EntryPoints")
  Entrypoints,
  @SerialName(value = "ApiDefinitions")
  Apidefinitions,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
