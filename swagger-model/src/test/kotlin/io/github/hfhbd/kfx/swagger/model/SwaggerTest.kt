package io.github.hfhbd.kfx.swagger.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SwaggerTest {
    @Test
    fun decodeLeanIXSwagger() {
        val text = SwaggerTest::class.java.getResourceAsStream("/leanix.json")!!.bufferedReader().readText()
        val swagger = json.decodeFromString(Swagger.serializer(), text)
        val inboundProcessor = swagger.definitions["InboundProcessor"]
        assertNotNull(inboundProcessor)
        assertEquals(11, inboundProcessor.properties.size)
    }

    @Test
    fun decodeSapCISwagger() {
        val text = SwaggerTest::class.java.getResourceAsStream("/sapci.json")!!.bufferedReader().readText()
        json.decodeFromString(Swagger.serializer(), text)
    }

    @Test
    fun decodeIdpSwagger() {
        val text = SwaggerTest::class.java.getResourceAsStream("/idp.json")!!.bufferedReader().readText()
        json.decodeFromString(Swagger.serializer(), text)
    }
}
