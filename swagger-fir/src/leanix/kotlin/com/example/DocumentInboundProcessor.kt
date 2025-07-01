package com.example

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DocumentInboundProcessor")
public data class DocumentInboundProcessor(
  /**
   * A not necessarily unique name for this processor
   */
  public override val processorName: String? = null,
  /**
   * A description for this processor
   */
  public override val processorDescription: String? = null,
  /**
   * Defines to which data object the processor will be applied
   */
  public override val filter: FilterConfig? = null,
  /**
   * Order of processor execution. Processors with high numbers are executed after such processors with low number. A value greater or equal 0 is expected for this value
   */
  public override val run: Int? = null,
  /**
   * Whether this processor is executed or not. Default is true.
   */
  public override val enabled: Boolean? = null,
  /**
   * When false, it produces warnings if forEach option is used and filter is not defined, also checks in a similar way the combined use of Search scope and forEach depending of the type of Inbound processor. If set to true, no warnings are generated
   */
  public override val allowManyIterations: Boolean? = null,
  /**
   * An EL expression which targets a content property with a list. Processing will be done for each element in the list.
   */
  public override val forEach: String? = null,
  /**
   * A list of variables. Each variable consists of a key and a value. Both fields can contain EL expressions. The expression in key must be evaluated to a single element while the expression in value will be evaluated to a list. The EL expressions are evaluated after a processor has run for a data object and the values will be provided in the next run of the running synchronization.
   */
  public override val variables: List<VariableKeyValueTemplate>,
  /**
   * The operation mode of this processor. It can be either 'delete', or 'createOrUpdate'.
   */
  public override val mode: String? = null,
  /**
   * The log level of this processor. It can be 'off', 'warning' or 'debug'.
   */
  public override val logLevel: String? = null,
  /**
   * A template which is evaluated to represent a unique identifier of a Fact Sheet, or a group of Fact Sheets with certain criteria
   */
  public val identifier: IdentifierWithSearchScopeTemplate,
  /**
   * A list of changes that are performed to the specified fact sheet
   */
  public val updates: List<PatchTemplate>,
) : InboundProcessor
