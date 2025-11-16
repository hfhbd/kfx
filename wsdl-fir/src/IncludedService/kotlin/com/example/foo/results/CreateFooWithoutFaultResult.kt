package com.example.foo.results

import com.example.bar.Bar
import io.github.hfhbd.kfx.soap11.Envelope
import io.github.hfhbd.kfx.soap11.Fault

public sealed interface CreateFooWithoutFaultResult {
  public data class Success(
    public val body: Envelope<Bar>,
  ) : CreateFooWithoutFaultResult

  public data class Failure(
    public val body: Envelope<Fault>,
  ) : CreateFooWithoutFaultResult
}
