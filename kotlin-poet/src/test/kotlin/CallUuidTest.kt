import com.squareup.kotlinpoet.NameAllocator
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.CallFunction
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.CallStatic
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.Chain
import io.github.hfhbd.kfx.kotlin.toCodeBlock
import kotlin.test.Test
import kotlin.test.assertEquals

class CallUuidTest {
    @Test
    fun call() {
        val uuid = CodeGenTree.NormalClass("kotlin.uuid", listOf("Uuid"))

        assertEquals(
            """kotlin.uuid.Uuid.random()""",
            Chain(
                lhs = CallStatic(
                    uuid,
                ),
                rhs = CallFunction(
                    function = CodeGenTree.Function(
                        name = "random",
                        emptyList(),
                        uuid,
                    ),
                    parameters = emptyList(),
                ),
            ).toCodeBlock(nameAllocator = NameAllocator()).toString(),
        )
    }
}
