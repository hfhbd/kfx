package server

import com.example.FooInput
import kotlin.String
import org.springframework.web.bind.`annotation`.RequestBody
import org.springframework.web.bind.`annotation`.RequestMapping
import org.springframework.web.bind.`annotation`.RequestMethod.POST
import org.springframework.web.bind.`annotation`.ResponseStatus

public interface BazA {
  /**
   * Foo Bar API
   */
  @RequestMapping(
    name = "bazA",
    method = [POST],
    path = ["/http/foo/bar/baz"],
    consumes = ["application/json"],
    produces = ["application/json"],
  )
  @ResponseStatus(value = org.springframework.http.HttpStatus.OK)
  public suspend fun bazA(@RequestBody input: FooInput): String
}
