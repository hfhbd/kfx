package com.sap.hci.api.client

import com.sap.hci.api.GetMDIDeltaTokenByOperationAndEntityAndVersion
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to get the delta token for the SAP Master Data Integration receiver adapter (MDI receiver adapter). This feature is only available with SAP Integration Suite. For more information on the MDI adapter, see SAP Help Portal documentation at [SAP Master Data Integration Receiver Adapter]( https://help.sap.com/docs/integration-suite/sap-integration-suite/e91e373bbb5b49ccbc2977152def61a2.html).
 *
 * @param operation Operation type <br>
 * Example: **READ**
 * @param entity Fully qualified entity type <br>
 * Example: **sap.odm.businesspartner.BusinessPartner**
 * @param version Entity version <br>
 * Example: **2.1.1**
 */
@Throws(Error::class)
public suspend fun HttpClient.getMDIDeltaTokenByOperationAndEntityAndVersion(
  operation: String,
  entity: String,
  version: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetMDIDeltaTokenByOperationAndEntityAndVersion? {
  val response = `get`(urlString = """MDIDeltaToken(Operation='${operation}',Entity='${entity}',Version='${version}')""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetMDIDeltaTokenByOperationAndEntityAndVersion>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
