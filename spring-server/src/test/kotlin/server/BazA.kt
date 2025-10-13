package server

import com.example.FooInput
import kotlin.String
import org.springframework.web.bind.`annotation`.PostMapping
import org.springframework.web.bind.`annotation`.ResponseStatus

public interface BazA {
  /**
   * Foo Bar API
   */
  @PostMapping(
    name = "bazA",
    path = ["/http/foo/bar/baz"],
    consumes = ["application/json"],
    produces = ["application/json"],
  )
  @ResponseStatus(value = org.springframework.http.HttpStatus.OK)
  public suspend fun bazA(input: FooInput): String
}
