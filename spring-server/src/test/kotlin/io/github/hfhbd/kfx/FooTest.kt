package io.github.hfhbd.kfx

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
internal class FooTest(@Autowired private val mockMvc: MockMvc) {

    @Test
    @Throws(Exception::class)
    fun shouldReturnDefaultHelloWorld() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpect(MockMvcResultMatchers.request().asyncStarted())
            .andReturn()
        mockMvc.perform(asyncDispatch(result))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.content().string("Hello World!"))
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnCustomHello() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("name", "You"))
            .andExpect(MockMvcResultMatchers.request().asyncStarted())
            .andReturn()
        mockMvc.perform(asyncDispatch(result))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.content().string("Hello You!"))
    }
}
