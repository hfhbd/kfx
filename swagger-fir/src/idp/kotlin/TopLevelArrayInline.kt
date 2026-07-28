import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
public value class TopLevelArrayInline(
  public val `value`: List<TopLevelArrayInlineItems>,
)
