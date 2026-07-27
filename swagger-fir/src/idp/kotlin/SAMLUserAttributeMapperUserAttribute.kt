import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = SAMLUserAttributeMapperUserAttribute.Companion::class)
public enum class SAMLUserAttributeMapperUserAttribute {
  `42`,
  ;

  public companion object : KSerializer<SAMLUserAttributeMapperUserAttribute> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("SAMLUserAttributeMapperUserAttribute", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, `value`: SAMLUserAttributeMapperUserAttribute) {
      encoder.encodeInt(value.name.toInt())
    }

    override fun deserialize(decoder: Decoder): SAMLUserAttributeMapperUserAttribute = valueOf(decoder.decodeInt().toString())
  }
}
