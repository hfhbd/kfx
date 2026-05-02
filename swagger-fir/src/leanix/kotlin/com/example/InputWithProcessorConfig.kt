package com.example

import kotlinx.serialization.Serializable

@Serializable
public data class InputWithProcessorConfig(
  /**
   * The input that is processed
   */
  public val input: Input,
  /**
   * The processors and variables that are required to process the input
   */
  public val processorConfiguration: ProcessorConfiguration,
)
