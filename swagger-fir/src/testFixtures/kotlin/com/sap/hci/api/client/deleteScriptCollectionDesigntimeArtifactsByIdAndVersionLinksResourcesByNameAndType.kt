package com.sap.hci.api.client

import com.sap.hci.api.DeleteScriptCollectionDesigntimeArtifactsByIdAndVersionLinksResourcesByNameAndTypeReferencedResourceType
import com.sap.hci.api.DeleteScriptCollectionDesigntimeArtifactsByIdAndVersionLinksResourcesByNameAndTypeType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to delete a resource of a script collection.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html).<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update integration flows.
 *
 * @param id Id of script collection artifact <br>
 * Example: **ScriptCollection1**
 * @param version Version of script collection artifact  <br>
 * You can enter either **active** or the version name (e.g. *1.0.5**) for retrieving the current version.
 * @param name Resource name <br>
 * Example: ```AddSignatureInfo.xsl```
 * @param type Resource type <br>
 * Example: ```xslt```
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 * @param referencedResourceType Reference to another resource type (only for resource type 'xsd' the reference to 'wsdl' is allowed).
 */
@Throws(Error::class)
public suspend fun HttpClient.deleteScriptCollectionDesigntimeArtifactsByIdAndVersionLinksResourcesByNameAndType(
  id: String,
  version: String = "active",
  name: String,
  type: DeleteScriptCollectionDesigntimeArtifactsByIdAndVersionLinksResourcesByNameAndTypeType,
  X_CSRF_Token: String,
  referencedResourceType: DeleteScriptCollectionDesigntimeArtifactsByIdAndVersionLinksResourcesByNameAndTypeReferencedResourceType? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """ScriptCollectionDesigntimeArtifacts(Id='${id}',Version='${version}')/${'$'}links/Resources(Name='${name}',ResourceType='${type}')""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    parameter("referencedResourceType", referencedResourceType)
    contentType(Json)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    val output = response.body<Error>()
    throw output
  }
}
