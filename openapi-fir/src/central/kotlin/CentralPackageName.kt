import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.plugins.packagename.PackageName

@ServiceLoader(IrTransformer::class)
class CentralPackageName : IrTransformer by PackageName("dev.central")
