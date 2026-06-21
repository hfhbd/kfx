package com.jira

import kotlin.String
import kotlin.collections.Set
import kotlin.collections.emptySet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Locale")
public data class Locale(
  public val country: String? = null,
  public val displayCountry: String? = null,
  public val displayLanguage: String? = null,
  public val displayName: String? = null,
  public val displayScript: String? = null,
  public val displayVariant: String? = null,
  public val extensionKeys: Set<String>? = emptySet(),
  public val iso3Country: String? = null,
  public val iso3Language: String? = null,
  public val language: String? = null,
  public val script: String? = null,
  public val unicodeLocaleAttributes: Set<String>? = emptySet(),
  public val unicodeLocaleKeys: Set<String>? = emptySet(),
  public val variant: String? = null,
)
