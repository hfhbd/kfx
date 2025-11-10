package com.example.foo.results

import com.example.bar.Bar
import com.example.foo.Fault
import io.github.hfhbd.kfx.soap11.Envelope

sealed interface CreateFooResult {
  data class Success(val body: Envelope<Bar>) : CreateFooResult
  data class Failure(val body: Envelope<Fault>) : CreateFooResult
}
