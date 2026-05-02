package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class Locale(
  public val country: String? = null,
  public val displayCountry: String? = null,
  public val displayLanguage: String? = null,
  public val displayName: String? = null,
  public val displayScript: String? = null,
  public val displayVariant: String? = null,
  public val extensionKeys: List<String> = emptyList(),
  public val iso3Country: String? = null,
  public val iso3Language: String? = null,
  public val language: String? = null,
  public val script: String? = null,
  public val unicodeLocaleAttributes: List<String> = emptyList(),
  public val unicodeLocaleKeys: List<String> = emptyList(),
  public val variant: String? = null,
)
