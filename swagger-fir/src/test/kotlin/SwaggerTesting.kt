import io.github.hfhbd.kfx.KotlinPoetCodeGenerator
import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.codegen.create
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.OData
import io.github.hfhbd.kfx.ir.PackageName
import io.github.hfhbd.kfx.swagger.Swagger
import io.github.hfhbd.kfx.swagger.json
import io.github.hfhbd.kfx.swagger.toIr
import java.util.*
import kotlin.io.path.*
import kotlin.test.*

class SwaggerTesting {
    private fun getSwagger(swaggerFile: String): IRTree {
        val wsdl = SwaggerTesting::class.java.getResourceAsStream(swaggerFile)!!.bufferedReader().readText()
        val ir = json.decodeFromString(Swagger.serializer(), wsdl).toIr()
        return ir
    }

    private val kotlinTestFixtures = Path(System.getenv("testFixtures")) / "kotlin"
    val codeGenCreator = ServiceLoader.load(CodeGenCreator::class.java).single()

    @Test
    fun leanIX() {
        var swagger = getSwagger("/leanix.json")
        swagger = PackageName("com.example")(swagger)
        val codeGen = codeGenCreator.create(swagger)
        val codeGenerators = ServiceLoader.load(CodeGenerator::class.java).map {
            it as KotlinPoetCodeGenerator
        }

        codeGenerators.test(codeGen)
    }

    @Test
    fun sapci() {
        var swagger = getSwagger("/IntegrationContent.json")
        swagger = PackageName("com.sap.hci.api")(swagger)
        swagger = OData()(swagger)
        val codeGen = codeGenCreator.create(swagger)
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
