package io.github.hfhbd.kfx

import com.example.FooInput
import org.springframework.web.bind.annotation.RestController
import server.BazA
import server.BazACsrfToken

@RestController
class FooController : BazA, BazACsrfToken {
    override suspend fun bazA(input: FooInput): String {
        return "bazA"
    }

    override suspend fun bazACsrfToken() {}
}
