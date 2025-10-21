import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.plugins.packagename.PackageName

@ServiceLoader(IrTransformer::class)
class JiraPackageName : IrTransformer by PackageName("com.jira")
