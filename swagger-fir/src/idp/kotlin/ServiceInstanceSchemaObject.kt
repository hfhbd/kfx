import kotlinx.serialization.Serializable

@Serializable
public data class ServiceInstanceSchemaObject(
  public val create: SchemaParameters? = null,
  public val update: SchemaParameters? = null,
)
