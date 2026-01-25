import kotlinx.serialization.Serializable

@Serializable
public data class SchemaParameters(
  public val parameters: JSONSchemaObject? = null,
)
