package com.sap.hci.api.server

import com.sap.hci.api.GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdDefaultValMaps
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all the default value mappings for a specific bidirectional agency identifier.
 */
public fun Route.getValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdDefaultValMaps(action: suspend ApplicationCall.() -> GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdDefaultValMaps) {
  route(path = """/ValueMappingDesigntimeArtifacts(Id='{id}',Version='{version}')/ValMapSchema(SrcAgency='{srcAgency}',SrcId='{srcId}',TgtAgency='{tgtAgency}',TgtId='{tgtId}')/DefaultValMaps""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
