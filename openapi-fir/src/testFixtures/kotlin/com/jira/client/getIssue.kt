package com.jira.client

import com.jira.IssueBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get issue for key
 * Returns a full representation of the issue for the given issue key.
 * An issue JSON consists of the issue key, a collection of fields,
 * a link to the workflow transition sub-resource, and (optionally) the HTML rendered values of any fields that support it
 * (e.g. if wiki syntax is enabled for the description or comments).
 * The fields param (which can be specified multiple times) gives a comma-separated list of fields
 * to include in the response. This can be used to retrieve a subset of fields.
 * A particular field can be excluded by prefixing it with a minus.
 * By default, all (*all) fields are returned in this get-issue resource. Note: the default is different
 * when doing a jql search -- the default there is just navigable fields (*navigable).
 * - *all - include all fields
 * - *navigable - include just navigable fields
 * - summary,comment - include just the summary and comments
 * - -comment - include everything except comments (the default is *all for get-issue)
 * - *all,-comment - include everything except comments
 *
 * The {@code properties} param is similar to {@code fields} and specifies a comma-separated list of issue
 * properties to include. Unlike {@code fields}, properties are not included by default. To include them all
 * send {@code ?properties=*all}. You can also include only specified properties or exclude some properties
 * with a minus (-) sign.
 *
 * - {@code *all} - include all properties
 * - {@code *all, -prop1} - include all properties except {@code prop1}
 * - {@code prop1, prop1} - include {@code prop1} and {@code prop2} properties
 *
 * Jira will attempt to identify the issue by the issueIdOrKey path parameter. This can be an issue id,
 * or an issue key. If the issue cannot be found via an exact match, Jira will also look for the issue in a case-insensitive way,
 * by looking to see if the issue was moved. In either of these cases, the request will proceed as normal (a 302 or other redirect
 * will not be returned). The issue key contained in the response will indicate the current value of issue's key.
 *
 * The expand param is used to include, hidden by default, parts of response. This can be used to include:
 *
 * - renderedFields - field values in HTML format
 * - names - display name of each field
 * - schema - schema for each field which describes a type of the field
 * - transitions - all possible transitions for the given issue
 * - operations - all possibles operations which may be applied on issue
 * - editmeta - information about how each field may be edited. It contains field's schema as well.
 * - changelog - history of all changes of the given issue
 * - versionedRepresentations -
 * REST representations of all fields. Some field may contain more recent versions. RESET representations are numbered.
 * The greatest number always represents the most recent version. It is recommended that the most recent version is used.
 * version for these fields which provide a more recent REST representation.
 * After including versionedRepresentations "fields" field become hidden.
 *
 * @param issueIdOrKey Issue id or key
 * @param expand The expand param is used to include, hidden by default, parts of response. This can be used to include: renderedFields, names, schema, transitions, operations, editmeta, changelog, versionedRepresentations.
 * @param fields The list of fields to return for the issue. By default, all fields are returned.
 * @param updateHistory The updateHistory param adds the issues retrieved by this method to the current user's issue history
 * @param properties The list of properties to return for the issue. By default no properties are returned.
 */
public suspend fun HttpClient.getIssue(
  issueIdOrKey: String,
  expand: String? = null,
  fields: String? = null,
  updateHistory: String? = null,
  properties: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IssueBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}""") {
    parameter("expand", expand)
    parameter("fields", fields)
    parameter("updateHistory", updateHistory)
    parameter("properties", properties)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueBean>()
  return output
}
