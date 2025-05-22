package com.sap.hci.api.client

import com.sap.hci.api.GetBuildAndDeployStatusByTaskId
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
 * You can use the following request to get the [Build and Deploy Status](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d8934e0d3ab649ecb5ae744663c7962c.html) of an artifact by task Id. For an example scenario that shows you how to use this request in a sequence with other API calls, refer to the SAP Help Portal documentation [Get Runtime Status of Deployed Integration Flow](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/49c733687c80415aa5b67d58cda99dbf.html).
 *
 * @param taskId Task Id of design time artifact
 */
@Throws(Error::class)
public suspend fun HttpClient.getBuildAndDeployStatusByTaskId(taskId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): GetBuildAndDeployStatusByTaskId? {
  val response = `get`(urlString = """BuildAndDeployStatus(TaskId='${taskId}')""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetBuildAndDeployStatusByTaskId>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
