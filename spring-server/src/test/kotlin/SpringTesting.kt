import kotlin.test.Test

class SpringTesting {
    @Test
    fun a() {
        test(
            "test",
            ignoreFiles = arrayOf(
                "SpringTesting.kt",
                "io/github/hfhbd/kfx/KtorServerGeneratorTest.kt",
                "io/github/hfhbd/kfx/FooApplication.kt",
                "io/github/hfhbd/kfx/FooController.kt",
                "io/github/hfhbd/kfx/FooTest.kt",
            ),
        )
    }
}
