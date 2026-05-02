package com.example

import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Definition of the entities to be removed if they are not touched by the processor configuration
 */
@Serializable
@SerialName(value = "DeletionScope")
public data class DeletionScope(
  /**
   * Maximum Ratio (percentage) between elements to be deleted compared to size of the scope. If this limit is violated the deletion is canceled
   */
  public val maximumDeletionRatio: Unit? = null,
  /**
   * The scopes for fact sheet entities
   */
  public val factSheets: List<FactSheetDeletionScope> = emptyList(),
  /**
   * The scopes for fact sheet entities
   */
  public val relations: List<RelationDeletionScope> = emptyList(),
  /**
   * The scopes for fact sheet entities
   */
  public val tags: List<TagDeletionScope> = emptyList(),
  /**
   * The scopes for subscriptions entities
   */
  public val subscriptions: List<SubscriptionDeletionScope> = emptyList(),
  /**
   * The scopes for document entities
   */
  public val documents: List<DocumentDeletionScope> = emptyList(),
  /**
   * The scopes for impact entities
   */
  public val impacts: List<ImpactDeletionScope> = emptyList(),
  /**
   * The scopes for To-dos entities
   */
  public val todos: List<TodoDeletionScope> = emptyList(),
)
