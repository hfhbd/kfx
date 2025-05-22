package com.sap.hci.api

import kotlinx.serialization.Serializable

@Serializable
public data class CreateCopyIntegrationPackage(
  public val d: IntegrationPackage? = null,
)
