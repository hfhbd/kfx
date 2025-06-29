package com.jira.client

import com.jira.AttachmentJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.http.ContentType.MultiPart.FormData
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Add one or more attachments to an issue
 * Add one or more attachments to an issue.
 * This resource expects a multipart post. The media-type multipart/form-data is defined in RFC 1867. Most client libraries have classes that make dealing with multipart posts simple. For instance, in Java the Apache HTTP Components library provides a MultiPartEntity that makes it simple to submit a multipart POST.
 * In order to protect against XSRF attacks, because this method accepts multipart/form-data, it has XSRF protection
 * on it. This means you must submit a header of X-Atlassian-Token: no-check with the request, otherwise it will be blocked.
 * The name of the multipart/form-data parameter that contains attachments must be file.
 * A simple example to upload a file called "myfile.txt" to issue TEST-123:
 * curl -D- -u admin:admin -X POST -H "X-Atlassian-Token: no-check" -F "file=@myfile.txt" http://myhost/rest/api/2/issue/TEST-123/attachments
 *
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.addAttachment(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): AttachmentJsonBean? {
  val response = post(urlString = """api/2/issue/${issueIdOrKey}/attachments""") {
    contentType(FormData)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AttachmentJsonBean>()
  return output
}
