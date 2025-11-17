import io.github.hfhbd.kfx.wsdl.fir.isXSD
import io.github.hfhbd.kfx.wsdl.fir.namespace
import io.github.hfhbd.kfx.wsdl.model.XSD_NAMESPACE
import nl.adaptivity.xmlutil.QName
import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class QNameTests {
    @Test
    fun testQName() {
        val string = QName(XSD_NAMESPACE, "string")
        assertTrue(string.isXSD())

        assertNull(QName("string").namespace)
    }
}
