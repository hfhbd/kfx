import kotlin.test.Test

class SpringTesting {
    @Test
    fun a() {
        test(
            "test",
            ignoreFiles = arrayOf(
                "SpringTesting.kt",
                "io/github/hfhbd/kfx/BazAController.kt",
                "io/github/hfhbd/kfx/BazATokenController.kt",
                "io/github/hfhbd/kfx/FooApplication.kt",
                "io/github/hfhbd/kfx/FooTest.kt",
            ),
        )
    }
}
