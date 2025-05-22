package io.github.hfhbd.kfx.swagger

import kotlin.test.*

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
        val text = SwaggerTest::class.java.getResourceAsStream("/IntegrationContent.json")!!.bufferedReader().readText()
        json.decodeFromString(Swagger.serializer(), text)
    }
}
