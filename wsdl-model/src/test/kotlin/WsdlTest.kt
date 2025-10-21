import io.github.hfhbd.kfx.wsdl.model.WSDL
import io.github.hfhbd.kfx.wsdl.model.xml
import kotlin.test.Test

class WsdlTest {
    @Test
    fun parseFooService() {
        val text = WsdlTest::class.java.getResourceAsStream("/FooService.wsdl")!!.bufferedReader().readText()
        xml().decodeFromString(WSDL.serializer(), text)
    }
}
