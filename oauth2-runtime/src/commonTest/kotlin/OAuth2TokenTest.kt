import io.github.hfhbd.kfx.oauth2.OAuth2Token
import kotlinx.serialization.json.Json
import kotlin.test.Test

class OAuth2TokenTest {
    @Test
    fun deserialize() {
        // language=JSON
        val response = """
            {
                "scope": "",
                "expired": false,
                "access_token": "",
                "token_type": "bearer",
                "expires_in": 3599
            }
        """.trimIndent()
        Json.decodeFromString(OAuth2Token.serializer(), response)
    }
}
