package results

import com.example.Fault
import kotlin.String

public sealed interface BazACsrfTokenResult {
  public data class Success(
    public val XCSRFToken: String,
  ) : BazACsrfTokenResult

  public data class Failure(
    public val body: Fault,
  ) : BazACsrfTokenResult
}
