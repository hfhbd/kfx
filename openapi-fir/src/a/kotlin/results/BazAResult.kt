package results

import com.example.Fault
import kotlin.String

public sealed interface BazAResult {
  public data class Success(
    public val body: String,
    public val logid: String,
  ) : BazAResult

  public data class Failure(
    public val body: Fault,
    public val logid: String,
  ) : BazAResult
}
