import kotlinx.serialization.Serializable

@Serializable
public data class SchemasObject(
  public val service_binding: ServiceBindingSchemaObject? = null,
  public val service_instance: ServiceInstanceSchemaObject? = null,
)
