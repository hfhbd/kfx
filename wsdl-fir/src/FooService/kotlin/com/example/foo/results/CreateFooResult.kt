package com.example.foo.results

import com.example.bar.Bar
import com.example.foo.Fault
import io.github.hfhbd.kfx.soap11.Envelope

public sealed interface CreateFooResult {
  public data class Success(
    public val body: Envelope<Bar>,
  ) : CreateFooResult

  public data class Failure(
    public val body: Envelope<Fault>,
  ) : CreateFooResult
}
