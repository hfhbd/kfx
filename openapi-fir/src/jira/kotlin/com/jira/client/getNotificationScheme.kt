package com.jira.client

import com.jira.NotificationSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get full notification scheme details
 * Returns a full representation of the notification scheme for the given id. This resource will return a
 * notification scheme containing a list of events and recipient configured to receive notifications for these events. Consumer
 * should allow events without recipients to appear in response. User accessing
 * the data is required to have permissions to administer at least one project associated with the requested notification scheme.
 * Notification recipients can be:
 * - current assignee - the value of the notificationType is CurrentAssignee
 * - issue reporter - the value of the notificationType is Reporter
 * - current user - the value of the notificationType is CurrentUser
 * - project lead - the value of the notificationType is ProjectLead
 * - component lead - the value of the notificationType is ComponentLead
 * - all watchers - the value of the notification type is AllWatchers
 * <li>configured user - the value of the notification type is User. Parameter will contain key of the user. Information about the user will be provided
 * if <b>user</b> expand parameter is used.
 * - configured group - the value of the notification type is Group. Parameter will contain name of the group. Information about the group will be provided
 * if <b>group</b> expand parameter is used.
 * - configured email address - the value of the notification type is EmailAddress, additionally information about the email will be provided.
 * - users or users in groups in the configured custom fields - the value of the notification type is UserCustomField or GroupCustomField. Parameter
 * will contain id of the custom field. Information about the field will be provided if <b>field</b> expand parameter is used.
 * - configured project role - the value of the notification type is ProjectRole. Parameter will contain project role id. Information about the project role
 * will be provided if <b>projectRole</b> expand parameter is used.
 * Please see the example for reference.
 * The events can be Jira system events or events configured by administrator. In case of the system events, data about theirs
 * ids, names and descriptions is provided. In case of custom events, the template event is included as well.
 */
public suspend fun HttpClient.getNotificationScheme(
  id: Long,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): NotificationSchemeBean? {
  val response = `get`(urlString = """api/2/notificationscheme/${id}""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<NotificationSchemeBean>()
  return output
}
