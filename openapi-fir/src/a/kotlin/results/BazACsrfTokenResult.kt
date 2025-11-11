package results

import com.example.Fault

public sealed interface BazACsrfTokenResult {
  public data class Success(
    public val xCsrfToken: String,
  ) : BazACsrfTokenResult

  public data class Failure(
    public val body: Fault,
  ) : BazACsrfTokenResult
}
