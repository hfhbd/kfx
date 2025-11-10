package com.example.foo.results

import com.example.bar.Bar
import io.github.hfhbd.kfx.soap11.Fault
import io.github.hfhbd.kfx.soap11.Envelope

sealed interface CreateFooWithoutFaultResult {
  data class Success(val body: Envelope<Bar>) : CreateFooWithoutFaultResult
  data class Failure(val body: Envelope<Fault>) : CreateFooWithoutFaultResult
}
