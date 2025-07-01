package com.jira.server

import com.jira.PasswordPolicyUpdateUserBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.String

/**
 * Get reasons for password policy disallowance on user password update
 * Returns a list of statements explaining why the password policy would disallow a proposed new password for a user with an existing password.
 * You can use this method to test the password policy validation. This could be done prior to an action where the password
 * is actually updated, using methods like ChangePassword or ResetPassword.
 * For example, you could use this to validate a password in a change password form in the user interface, as the user enters it.
 * The user must exist and the username and new password must be not empty, to perform the validation.
 * Note, this method will help you validate against the policy only. It won't check any other validations that might be performed
 * when submitting a password change/reset request, e.g. verifying whether the old password is valid.
 */
public fun Route.policyCheckUpdateUser(action: suspend ApplicationCall.(PasswordPolicyUpdateUserBean) -> String) {
  route(path = """/api/2/password/policy/updateUser""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<PasswordPolicyUpdateUserBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
