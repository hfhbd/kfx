@file:OptIn(InternalSerializationApi::class)

package io.github.hfhbd.kfx.openapi

import kotlinx.serialization.*
import kotlin.test.*

class OpenApiTest {
    @Test
    fun decode() {
        val input = OpenApiTest::class.java.getResourceAsStream("/a.json")!!.bufferedReader().readText()
        json.decodeFromString(OpenApi.serializer(), input)
    }

    @Test
    fun decodeSealed() {
        val input = OpenApiTest::class.java.getResourceAsStream("/sealed.json")!!.bufferedReader().readText()
        json.decodeFromString(OpenApi.serializer(), input)
    }

    @Test
    fun decodeGitHub() {
        val input = OpenApiTest::class.java.getResourceAsStream("/github.json")!!.bufferedReader().readText()
        json.decodeFromString(OpenApi.serializer(), input)
    }

    @Test
    fun decodeCentral() {
        val input = OpenApiTest::class.java.getResourceAsStream("/central.json")!!.bufferedReader().readText()
        json.decodeFromString(OpenApi.serializer(), input)
    }

    @Test
    fun decodeJira() {
        val input = OpenApiTest::class.java.getResourceAsStream("/jira.json")!!.bufferedReader().readText()
        json.decodeFromString(OpenApi.serializer(), input)
    }

    @Test
    fun decodeSecurity() {
        val s = """       
            {
              "type": "oauth2",
              "flows": {
                "implicit": {
                  "authorizationUrl": "https://example.com/api/oauth/dialog",
                  "scopes": {
                    "write:pets": "modify pets in your account",
                    "read:pets": "read your pets"
                  }
                }
                }
            }
        """.trimIndent()
        json.decodeFromString(OpenApi.SecurityScheme.serializer(), s)
    }
}
