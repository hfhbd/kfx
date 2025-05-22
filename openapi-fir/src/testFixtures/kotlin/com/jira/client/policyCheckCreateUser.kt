package com.jira.client

import com.jira.PasswordPolicyCreateUserBean
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
public suspend fun HttpClient.policyCheckCreateUser(input: PasswordPolicyCreateUserBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): String {
  val response = post(urlString = """api/2/password/policy/createUser""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<String>()
  return output
}
