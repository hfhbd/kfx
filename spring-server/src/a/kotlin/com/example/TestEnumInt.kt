package com.example

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = TestEnumInt.Companion::class)
public enum class TestEnumInt {
  `1`,
  `2`,
  ;

  public companion object : KSerializer<TestEnumInt> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("com.example.TestEnumInt", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, `value`: TestEnumInt) {
      encoder.encodeInt(value.name.toInt())
    }

    override fun deserialize(decoder: Decoder): TestEnumInt = valueOf(decoder.decodeInt().toString())
  }
}
