package io.github.hfhbd.kfx

import kotlin.test.*

class StatusCodesTest {
    @Test
    fun statusCodes() {
        assertEquals(StatusCodes("200", "400"), setOf("200", "400").getStatusCodes())
        assertEquals(StatusCodes("200", "400"), setOf("200", "400", "500").getStatusCodes())

        assertEquals(StatusCodes("200", "default"), setOf("200", "default").getStatusCodes())
        assertEquals(StatusCodes("200", "default"), setOf("200", "400", "default").getStatusCodes())

        assertEquals(StatusCodes("default", "400"), setOf("default", "400").getStatusCodes())
        assertEquals(StatusCodes("default", "400"), setOf("default", "400", "500").getStatusCodes())

        assertEquals(StatusCodes(null, null), setOf("default").getStatusCodes())
        assertEquals(StatusCodes("200", null), setOf("200").getStatusCodes())
        assertEquals(StatusCodes(null, "400"), setOf("400").getStatusCodes())

        assertEquals(StatusCodes("200", "4XX"), setOf("200", "4XX").getStatusCodes())
    }
}
