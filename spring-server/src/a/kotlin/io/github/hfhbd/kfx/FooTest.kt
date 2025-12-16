package io.github.hfhbd.kfx

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
internal class FooTest(@Autowired private val webClient: WebTestClient) {
    @Test
    fun bazA() {
        webClient
            .post().uri("/http/foo/bar/baz")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("""{ "s": "ff" }""")
            .exchange()
            .expectStatus().isOk
            .expectBody<String>().isEqualTo("bazA")
    }

    @Test
    fun error() {
        webClient
            .head().uri("/http/foo/bar/baz")
            .exchange()
            .expectStatus().is5xxServerError
    }
}
