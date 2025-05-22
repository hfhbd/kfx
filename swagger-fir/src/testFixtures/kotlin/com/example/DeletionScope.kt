package com.example

import kotlin.Unit
import kotlin.collections.List
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
  public val factSheets: List<FactSheetDeletionScope>,
  /**
   * The scopes for fact sheet entities
   */
  public val relations: List<RelationDeletionScope>,
  /**
   * The scopes for fact sheet entities
   */
  public val tags: List<TagDeletionScope>,
  /**
   * The scopes for subscriptions entities
   */
  public val subscriptions: List<SubscriptionDeletionScope>,
  /**
   * The scopes for document entities
   */
  public val documents: List<DocumentDeletionScope>,
  /**
   * The scopes for impact entities
   */
  public val impacts: List<ImpactDeletionScope>,
  /**
   * The scopes for To-dos entities
   */
  public val todos: List<TodoDeletionScope>,
)
