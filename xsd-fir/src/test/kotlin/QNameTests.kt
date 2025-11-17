import io.github.hfhbd.kfx.xsd.fir.isXSD
import io.github.hfhbd.kfx.xsd.fir.namespace
import io.github.hfhbd.kfx.xsd.model.XSD_NAMESPACE
import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class QNameTests {
    @Test
    fun testQName() {
        val string = nl.adaptivity.xmlutil.QName(XSD_NAMESPACE, "string")
        assertTrue(string.isXSD())

        assertNull(nl.adaptivity.xmlutil.QName("string").namespace)
    }
}
