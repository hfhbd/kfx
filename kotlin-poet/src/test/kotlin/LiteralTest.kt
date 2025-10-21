import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.kotlin.toCodeBlock
import kotlin.test.Test
import kotlin.test.assertEquals

class LiteralTest {
    @Test
    fun literals() {
        assertEquals("42", CodeGenTree.Expression.IntLiteral(42).toCodeBlock().toString())
        assertEquals("42", CodeGenTree.Expression.ByteLiteral(42).toCodeBlock().toString())
        assertEquals("42", CodeGenTree.Expression.ShortLiteral(42).toCodeBlock().toString())

        assertEquals("'f'", CodeGenTree.Expression.CharLiteral('f').toCodeBlock().toString())
        assertEquals("\"foo\"", CodeGenTree.Expression.StringLiteral("foo").toCodeBlock().toString())
    }
}
