package com.sap.hci.api.client

import com.sap.hci.api.CreateCopyIntegrationPackage
import com.sap.hci.api.CreateCopyIntegrationPackageImportMode
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to copy an integration package from 'Discover' to 'Design' section. If the package already exists, either the existing package can be overwritten or a new package with a suffix for name and Id can be created. <br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to create an integration package.
 *
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 * @param id Id of integration package - enclosed in single quotes <br>
 * Example: ```'ERPtoSuccessFactorsEmployeeCentralEmployeeandOrganizationalData'```.<br>
 * The Id can be found in the package URL.
 * @param importMode If the package already exists, it can be overwritten by the copied package, or a new package could be created with a new suffix, which needs to be provided by query parameter 'Suffix'.<br>
 *  OVERWRITE_MERGE - With this mode, the package will be copied and overwritten to an existing/already copied package from Discover, would keep the configurations (of externalized parameters) intact for the existing/already copied package's artifacts.
 * @param suffix Suffix is required, if a package with the same name already exists and an additional copy of the same package should be created. The entered value must be enclosed in single quotes.<br>
 * Example: Entered Suffix ```'ABC'``` creates a new package, where ```.ABC``` is added to the name and to the artifacts Ids.<br>
 * Note: Only relevant for query parameter 'ImportMode' ```'CREATE_COPY'```.
 */
@Throws(Error::class)
public suspend fun HttpClient.createCopyIntegrationPackage(
  X_CSRF_Token: String,
  id: String? = null,
  importMode: CreateCopyIntegrationPackageImportMode? = null,
  suffix: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CreateCopyIntegrationPackage {
  val response = post(urlString = """CopyIntegrationPackage""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    parameter("id", id)
    parameter("importMode", importMode)
    parameter("suffix", suffix)
    builder()
  }
  if (response.status.isSuccess()) {
    return response.body<CreateCopyIntegrationPackage>()
  } else {
    throw response.body<Error>()
  }
}
