package com.example

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator(discriminator = "processorType")
@SerialName(value = "InboundProcessor")
public sealed interface InboundProcessor {
  /**
   * A not necessarily unique name for this processor
   */
  public val processorName: String?

  /**
   * A description for this processor
   */
  public val processorDescription: String?

  /**
   * Defines to which data object the processor will be applied
   */
  public val filter: FilterConfig?

  /**
   * Order of processor execution. Processors with high numbers are executed after such processors with low number. A value greater or equal 0 is expected for this value
   */
  public val run: Int?

  /**
   * Whether this processor is executed or not. Default is true.
   */
  public val enabled: Boolean?

  /**
   * When false, it produces warnings if forEach option is used and filter is not defined, also checks in a similar way the combined use of Search scope and forEach depending of the type of Inbound processor. If set to true, no warnings are generated
   */
  public val allowManyIterations: Boolean?

  /**
   * An EL expression which targets a content property with a list. Processing will be done for each element in the list.
   */
  public val forEach: String?

  /**
   * A list of variables. Each variable consists of a key and a value. Both fields can contain EL expressions. The expression in key must be evaluated to a single element while the expression in value will be evaluated to a list. The EL expressions are evaluated after a processor has run for a data object and the values will be provided in the next run of the running synchronization.
   */
  public val variables: List<VariableKeyValueTemplate>

  /**
   * The operation mode of this processor. It can be either 'delete', or 'createOrUpdate'.
   */
  public val mode: String?

  /**
   * The log level of this processor. It can be 'off', 'warning' or 'debug'.
   */
  public val logLevel: String?
}
