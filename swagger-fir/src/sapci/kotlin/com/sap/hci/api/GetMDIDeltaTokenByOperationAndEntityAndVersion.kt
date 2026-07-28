package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetMDIDeltaTokenByOperationAndEntityAndVersion")
public data class GetMDIDeltaTokenByOperationAndEntityAndVersion(
  public val d: MDIDeltaToken? = null,
)
