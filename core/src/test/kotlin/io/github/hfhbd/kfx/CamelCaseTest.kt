package io.github.hfhbd.kfx

import kotlin.test.Test
import kotlin.test.assertEquals

class CamelCaseTest {
    @Test
    fun toCamelCase() {
        assertEquals("foobar", "foobar".toCamelCase())
        assertEquals("FooBar", "FooBar".toCamelCase())
        assertEquals("fooBar", "fooBar".toCamelCase())
        assertEquals("com.example.Foobar", "com.example.Foobar".toCamelCase())
        assertEquals("com.example.FooBar", "com.example.FooBar".toCamelCase())
        assertEquals("com.example.FooBar", "com.example.Foo_Bar".toCamelCase())
    }

    @Test
    fun toCamelCaseEnumValue() {
        assertEquals("Foobar", "foobar".toPascalCaseEnumValue())
        assertEquals("FooBar", "FooBar".toPascalCaseEnumValue())
        assertEquals("FooBar", "foo.Bar".toPascalCaseEnumValue())
        assertEquals("FooBar", "Foo.Bar".toPascalCaseEnumValue())
        assertEquals("FooBar", "FOO_BAR".toPascalCaseEnumValue())
        assertEquals("FooBar", "Foo Bar".toPascalCaseEnumValue())
    }

    @Test
    fun operationIdToCamelCase() {
        assertEquals("foobar", "foobar".operationIdToCamelCase())
        assertEquals("fooBar", "fooBar".operationIdToCamelCase())
        assertEquals("fooBar", "foo_bar".operationIdToCamelCase())
        assertEquals("fooBar", "foo-bar".operationIdToCamelCase())
    }
}
