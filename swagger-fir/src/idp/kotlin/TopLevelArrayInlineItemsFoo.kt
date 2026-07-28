import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = TopLevelArrayInlineItemsFoo.Companion::class)
public enum class TopLevelArrayInlineItemsFoo {
  `42`,
  ;

  public companion object : KSerializer<TopLevelArrayInlineItemsFoo> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("TopLevelArrayInlineItemsFoo", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, `value`: TopLevelArrayInlineItemsFoo) {
      encoder.encodeInt(value.name.toInt())
    }

    override fun deserialize(decoder: Decoder): TopLevelArrayInlineItemsFoo = valueOf(decoder.decodeInt().toString())
  }
}
