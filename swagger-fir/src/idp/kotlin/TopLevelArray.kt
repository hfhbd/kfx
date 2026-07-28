import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
public value class TopLevelArray(
  public val `value`: List<SAMLUserAttributeMapper>,
)
