import io.github.hfhbd.kfx.soap.Body
import io.github.hfhbd.kfx.soap.Envelope
import io.github.hfhbd.kfx.soap.Fault
import io.github.hfhbd.kfx.soap.Header
import kotlinx.serialization.builtins.PairSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import nl.adaptivity.xmlutil.serialization.XML
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializationTest {
    @Test
    fun serializeWithoutHeaders() {
        val xml = XML
        val someMessage = Envelope(
            header = null,
            body = Body(Pair("foo", 42)),
        )
        val faultMessage = Envelope(
            header = null,
            body = Body(Fault(faultCode = "soap:Server", faultString = "Some Error")),
        )

        val someMessageXml = xml.encodeToString(
            Envelope.serializer(PairSerializer(String.serializer(), Int.serializer())),
            someMessage,
        )
        val faultMessageXml = xml.encodeToString(
            Envelope.serializer(Fault.serializer()),
            faultMessage,
        )
        // language=xml
        assertEquals(
            """<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/"><Body><Pair first="foo" second="42" /></Body></Envelope>""",
            someMessageXml,
        )
        assertEquals(
            expected = someMessage,
            actual = xml.decodeFromString(
                Envelope.serializer(PairSerializer(String.serializer(), Int.serializer())),
                someMessageXml,
            ),
        )

        // language=xml
        assertEquals(
            """<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/"><Body><Fault><faultcode>soap:Server</faultcode><faultstring>Some Error</faultstring></Fault></Body></Envelope>""",
            faultMessageXml,
        )
        assertEquals(
            expected = faultMessage,
            actual = xml.decodeFromString(Envelope.serializer(Fault.serializer()), faultMessageXml),
        )
    }

    @Test
    fun serializeWithCustomHeaders() {
        val xml = XML(
            serializersModule = SerializersModule {
                contextual(Header::class) {
                    MyHeader.serializer()
                }
            },
        )
        val someMessage = Envelope(
            header = MyHeader(to = "to"),
            body = Body(Pair("foo", 42)),
        )
        val faultMessage = Envelope(
            header = MyHeader(to = "to"),
            body = Body(Fault(faultCode = "soap:Server", faultString = "Some Error")),
        )

        val someMessageXml = xml.encodeToString(
            Envelope.serializer(PairSerializer(String.serializer(), Int.serializer())),
            someMessage,
        )
        val faultMessageXml = xml.encodeToString(
            Envelope.serializer(Fault.serializer()),
            faultMessage,
        )
        // language=xml
        assertEquals(
            """<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/"><Header><to>to</to></Header><Body><Pair first="foo" second="42" /></Body></Envelope>""",
            someMessageXml,
        )
        assertEquals(
            expected = someMessage,
            actual = xml.decodeFromString(
                Envelope.serializer(PairSerializer(String.serializer(), Int.serializer())),
                someMessageXml,
            ),
        )

        // language=xml
        assertEquals(
            """<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/"><Header><to>to</to></Header><Body><Fault><faultcode>soap:Server</faultcode><faultstring>Some Error</faultstring></Fault></Body></Envelope>""",
            faultMessageXml,
        )
        assertEquals(
            expected = faultMessage,
            actual = xml.decodeFromString(
                Envelope.serializer(Fault.serializer()),
                faultMessageXml,
            ),
        )
    }
}
