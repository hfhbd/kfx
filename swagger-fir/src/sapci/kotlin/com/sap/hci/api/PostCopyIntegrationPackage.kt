package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PostCopyIntegrationPackage")
public data class PostCopyIntegrationPackage(
  public val d: IntegrationPackage? = null,
)
