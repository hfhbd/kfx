import io.github.hfhbd.kfx.KotlinCodeGenerator
import io.github.hfhbd.kfx.KotlinPoetCodeGenerator
import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.codegen.create
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.PackageName
import io.github.hfhbd.kfx.openapi.OpenApi
import io.github.hfhbd.kfx.openapi.json
import io.github.hfhbd.kfx.openapi.toIr
import java.util.*
import kotlin.io.path.*
import kotlin.test.*

class OpenApiTesting {
    private fun getOpenApi(openApiFile: String): IRTree {
        val openApi = OpenApiTesting::class.java.getResourceAsStream(openApiFile)!!.bufferedReader().readText()
        val ir = json.decodeFromString(OpenApi.serializer(), openApi).toIr()
        return ir
    }

    private val kotlinTestFixtures = Path(System.getenv("testFixtures")) / "kotlin"
    val codeGenCreator = ServiceLoader.load(CodeGenCreator::class.java).single()

    @Test
    fun a() {
        val openApi = getOpenApi("/a.json")
        val codeGen = codeGenCreator.create(openApi)
        val codeGenerators = ServiceLoader.load(CodeGenerator::class.java).map {
            it as KotlinPoetCodeGenerator
        }

        codeGenerators.test(codeGen)
    }

    @Test
    fun sealed() {
        val openApi = getOpenApi("/sealed.json")
        val codeGen = codeGenCreator.create(openApi)
        val codeGenerators = ServiceLoader.load(CodeGenerator::class.java).map {
            it as KotlinPoetCodeGenerator
        }

        codeGenerators.test(codeGen)
    }

    @Test
    fun jira() {
        var openApi = getOpenApi("/jira.json")
        openApi = PackageName("com.jira")(openApi)
        val codeGen = codeGenCreator.create(openApi)
        val codeGenerators = ServiceLoader.load(CodeGenerator::class.java).map {
            it as KotlinPoetCodeGenerator
        }

        codeGenerators.test(codeGen)
    }

    @Test
    fun central() {
        var openApi = getOpenApi("/central.json")
        openApi = PackageName("dev.central")(openApi)
        val codeGen = codeGenCreator.create(openApi)
        val codeGenerators = ServiceLoader.load(CodeGenerator::class.java).map {
            it as KotlinPoetCodeGenerator
        }

        codeGenerators.test(codeGen)
    }

    @Test
    @Ignore
    fun gitHub() {
        val openApi = getOpenApi("/github.json")
        val codeGen = codeGenCreator.create(openApi)
        val codeGenerators = ServiceLoader.load(CodeGenerator::class.java).map {
            it as KotlinPoetCodeGenerator
        }

        codeGenerators.test(codeGen)
    }

    private fun List<KotlinPoetCodeGenerator>.test(codeGenTree: CodeGenTree) {
        for (codeGenerator in this) {
            for (fileSpec in codeGenerator.generateFileSpec(codeGenTree)) {
                val javaFile = fileSpec.toJavaFileObject()
                val expected = kotlinTestFixtures / javaFile.toUri().path
                assertEquals(expected.readText(), javaFile.openInputStream().bufferedReader().readText())
            }
        }
    }
}
