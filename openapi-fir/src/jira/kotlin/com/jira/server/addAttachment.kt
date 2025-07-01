package com.jira.server

import com.jira.AttachmentJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.ContentType.MultiPart.FormData
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Add one or more attachments to an issue
 * Add one or more attachments to an issue.
 * This resource expects a multipart post. The media-type multipart/form-data is defined in RFC 1867. Most client libraries have classes that make dealing with multipart posts simple. For instance, in Java the Apache HTTP Components library provides a MultiPartEntity that makes it simple to submit a multipart POST.
 * In order to protect against XSRF attacks, because this method accepts multipart/form-data, it has XSRF protection
 * on it. This means you must submit a header of X-Atlassian-Token: no-check with the request, otherwise it will be blocked.
 * The name of the multipart/form-data parameter that contains attachments must be file.
 * A simple example to upload a file called "myfile.txt" to issue TEST-123:
 * curl -D- -u admin:admin -X POST -H "X-Atlassian-Token: no-check" -F "file=@myfile.txt" http://myhost/rest/api/2/issue/TEST-123/attachments
 */
public fun Route.addAttachment(action: suspend ApplicationCall.() -> AttachmentJsonBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/attachments""") {
    contentType(FormData) {
      accept(Json) {
        post {
          val response = call.action()
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
