package results

import com.example.Fault
import kotlin.String

public sealed interface DeleteHttpFooBarBazResult {
  public data class Success(
    public val body: String,
    public val logid: String? = null,
  ) : DeleteHttpFooBarBazResult

  public data class Failure(
    public val body: Fault,
    public val logid: String? = null,
  ) : DeleteHttpFooBarBazResult
}
