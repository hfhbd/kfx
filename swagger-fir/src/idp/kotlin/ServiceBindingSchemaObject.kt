import kotlinx.serialization.Serializable

@Serializable
public data class ServiceBindingSchemaObject(
  public val create: SchemaParameters? = null,
)
