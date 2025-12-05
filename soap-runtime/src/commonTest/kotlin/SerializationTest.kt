import io.github.hfhbd.kfx.soap11.Envelope
import io.github.hfhbd.kfx.soap11.Fault
import io.github.hfhbd.kfx.soap11.Header
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.PairSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializationTest {
    @Test
    fun serializeWithoutHeaders() {
        val xml = XML
        val someMessage = Envelope(
            header = null,
            body = Pair("foo", 42),
        )
        val faultMessage = Envelope(
            header = null,
            body = Fault(faultCode = "soap:Server", faultString = "Some Error"),
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
            """<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/"><Body><Fault><faultcode xmlns="">soap:Server</faultcode><faultstring xmlns="">Some Error</faultstring></Fault></Body></Envelope>""",
            faultMessageXml,
        )
        assertEquals(
            expected = faultMessage,
            actual = xml.decodeFromString(Envelope.serializer(Fault.serializer()), faultMessageXml),
        )
    }

    @Test
    fun faultExample() {
        val xml = XML

        val faultMessage = Envelope(
            header = null,
            body = Fault(faultCode = "SOAP-ENV:Server", faultString = "Server Error"),
        )

        assertEquals(
            expected = faultMessage,
            actual = xml.decodeFromString(
                Envelope.serializer(Fault.serializer()),
                // language=xml
                """<SOAP-ENV:Envelope
        xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Body>
        <SOAP-ENV:Fault>
            <faultcode>SOAP-ENV:Server</faultcode>
            <faultstring>Server Error</faultstring>
        </SOAP-ENV:Fault>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>""",
            ),
        )
    }

    @Test
    fun faultExampleWithStringDetails() {
        val xml = XML(serializersModule = SerializersModule {
            include(Fault.serializerModule())
        }) {
            repairNamespaces = false
            xmlVersion = XmlVersion.XML10
            xmlDeclMode = XmlDeclMode.Charset
            autoPolymorphic = true
            
            indentString = "    "
        }

        val faultMessage = Envelope(
            header = null,
            body = Fault(
                faultCode = "SOAP-ENV:Server",
                faultString = "Server Error",
                detail = "FooString",
            ),
        )

        assertEquals(
            expected = faultMessage,
            actual = xml.decodeFromString(
                Envelope.serializer(Fault.serializer()),
                // language=xml
                """<SOAP-ENV:Envelope
        xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Body>
        <SOAP-ENV:Fault>
            <faultcode>SOAP-ENV:Server</faultcode>
            <faultstring>Server Error</faultstring>
            <detail>FooString</detail>
        </SOAP-ENV:Fault>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>""",
            ),
        )

        assertEquals(
            // language=xml
            expected = """<?xml version='1.0' encoding='UTF-8' ?>
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <Fault>
            <faultcode xmlns="">SOAP-ENV:Server</faultcode>
            <faultstring xmlns="">Server Error</faultstring>
            <detail xmlns="">FooString</detail>
        </Fault>
    </Body>
</Envelope>""",
            actual = xml.encodeToString(
                Envelope.serializer(Fault.serializer()),
                faultMessage,
            ),
        )
    }

    @Test
    fun faultExampleWithTypedDetails() {
        val xml = XML(
            serializersModule = SerializersModule {
                polymorphic(Any::class, FooString::class, FooString.serializer())
            }
        ) {
            repairNamespaces = false
            xmlVersion = XmlVersion.XML10
            xmlDeclMode = XmlDeclMode.Charset
            autoPolymorphic = true
            
            indentString = "    "
        }

        val faultMessage = Envelope(
            header = null,
            body = Fault(
                faultCode = "SOAP-ENV:Server",
                faultString = "Server Error",
                detail = FooString,
            ),
        )

        assertEquals(
            expected = faultMessage,
            actual = xml.decodeFromString(
                Envelope.serializer(Fault.serializer()),
                // language=xml
                """<SOAP-ENV:Envelope
        xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Body>
        <SOAP-ENV:Fault>
            <faultcode>SOAP-ENV:Server</faultcode>
            <faultstring>Server Error</faultstring>
            <detail><FooString xmlns="http://example.com"></FooString></detail>
        </SOAP-ENV:Fault>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>""",
            ),
        )

        assertEquals(
            // language=xml
            expected = """<?xml version='1.0' encoding='UTF-8' ?>
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <Fault>
            <faultcode xmlns="">SOAP-ENV:Server</faultcode>
            <faultstring xmlns="">Server Error</faultstring>
            <detail xmlns="">
                <FooString xmlns="http://example.com" />
            </detail>
        </Fault>
    </Body>
</Envelope>""",
            actual = xml.encodeToString(
                Envelope.serializer(Fault.serializer()),
                faultMessage,
            ),
        )
    }

    @Serializable
    @XmlSerialName("FooString", "http://example.com")
    data object FooString

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
            body = Pair("foo", 42),
        )
        val faultMessage = Envelope(
            header = MyHeader(to = "to"),
            body = Fault(faultCode = "soap:Server", faultString = "Some Error"),
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
            """<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/"><Header><to>to</to></Header><Body><Fault><faultcode xmlns="">soap:Server</faultcode><faultstring xmlns="">Some Error</faultstring></Fault></Body></Envelope>""",
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
