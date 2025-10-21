package io.github.hfhbd.kfx

import io.github.hfhbd.kfx.kotlin.ktor.server.toKtorServer
import kotlin.test.Test
import kotlin.test.assertEquals

class KtorServerGeneratorTest {
    @Test
    fun convertPath() {
        assertEquals("/asdf", "/asdf".toKtorServer())
        assertEquals("/asdf/{id}", "/asdf/\${id}".toKtorServer())
        assertEquals("/asdf/('id'='{id}')", "/asdf/('id'='\${id}')".toKtorServer())
    }
}
