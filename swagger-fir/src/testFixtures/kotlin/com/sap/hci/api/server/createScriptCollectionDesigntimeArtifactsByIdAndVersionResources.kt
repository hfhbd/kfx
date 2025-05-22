package com.sap.hci.api.server

import com.sap.hci.api.CreateScriptCollectionDesigntimeArtifactsByIdAndVersionResources
import com.sap.hci.api.ScriptCollectionDesigntimeArtifactResourceCreate
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * You can use the following request to add a resource to a script collection.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) .<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update script collections.
 */
public fun Route.createScriptCollectionDesigntimeArtifactsByIdAndVersionResources(action: suspend ApplicationCall.(ScriptCollectionDesigntimeArtifactResourceCreate) -> CreateScriptCollectionDesigntimeArtifactsByIdAndVersionResources) {
  route(path = """/ScriptCollectionDesigntimeArtifacts(Id='{id}',Version='{version}')/Resources""") {
    post {
      val body = call.receive<ScriptCollectionDesigntimeArtifactResourceCreate>()
      val response = call.action(body)
      call.response.status(Created)
      call.respond(response)
    }
  }
}
