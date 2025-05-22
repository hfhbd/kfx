# Module ir-packagename

This module adds the package name to all IR classes if there is no package name yet.

Unlike other modules, you need to add the service loader by yourself due the package name parameter:
```kotlin
@ServiceLoader(IrTransformer::class)
class MyPackageName : IrTransformer by PackageName("com.example")
```
