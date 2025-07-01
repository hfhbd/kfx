package com.jira.server

import com.jira.PasswordPolicyCreateUserBean
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
 * Get reasons for password policy disallowance on user creation
 * Returns a list of statements explaining why the password policy would disallow a proposed password for a new user.
 * You can use this method to test the password policy validation. This could be done prior to an action
 * where a new user and related password are created, using methods like the ones in
 * <a href="https://docs.atlassian.com/jira/latest/com/atlassian/jira/bc/user/UserService.html">UserService</a>.
 * For example, you could use this to validate a password in a create user form in the user interface, as the user enters it.
 * The username and new password must be not empty to perform the validation.
 * Note, this method will help you validate against the policy only. It won't check any other validations that might be performed
 * when creating a new user, e.g. checking whether a user with the same name already exists.
 */
public fun Route.policyCheckCreateUser(action: suspend ApplicationCall.(PasswordPolicyCreateUserBean) -> String) {
  route(path = """/api/2/password/policy/createUser""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<PasswordPolicyCreateUserBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
