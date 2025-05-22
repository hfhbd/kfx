package com.jira.client

import com.jira.PasswordPolicyUpdateUserBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

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
public suspend fun HttpClient.policyCheckUpdateUser(input: PasswordPolicyUpdateUserBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): String? {
  val response = post(urlString = """api/2/password/policy/updateUser""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<String>()
  return output
}
