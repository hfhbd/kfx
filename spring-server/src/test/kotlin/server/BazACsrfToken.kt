package server

import org.springframework.web.bind.`annotation`.RequestMapping
import org.springframework.web.bind.`annotation`.RequestMethod.HEAD
import org.springframework.web.bind.`annotation`.ResponseStatus

public interface BazACsrfToken {
  /**
   * Get the CSRF Token for BazA
   */
  @RequestMapping(
    name = "bazACsrfToken",
    method = [HEAD],
    path = ["/http/foo/bar/baz"],
  )
  @ResponseStatus(value = org.springframework.http.HttpStatus.OK)
  public suspend fun bazACsrfToken()
}
