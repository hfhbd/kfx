import kotlinx.serialization.Serializable

@Serializable
public data class TopLevelArrayInlineItems(
  /**
   * User attribute to take the value from
   */
  public val foo: TopLevelArrayInlineItemsFoo,
)
