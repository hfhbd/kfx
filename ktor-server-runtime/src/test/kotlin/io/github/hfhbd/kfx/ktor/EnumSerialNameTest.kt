package io.github.hfhbd.kfx.ktor

import kotlinx.serialization.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class EnumSerialNameTest {
    @Serializable
    enum class EnumSerialName {
        @SerialName("FOO")
        Foo,
        Bar,
        ;

        override fun toString(): String = serializer().descriptor.getElementName(ordinal)
    }

    @Test
    fun encode() {
        assertEquals("FOO", EnumSerialName.Foo.toString())
        assertEquals("Bar", EnumSerialName.Bar.toString())
    }

    @Test
    fun decode() {
        assertEquals(EnumSerialName.Foo, "FOO".toEnum())
        assertEquals(EnumSerialName.Bar, "Bar".toEnum())
        assertFailsWith<SerializationException> {
            "Foo".toEnum<EnumSerialName>()
        }
    }
}
