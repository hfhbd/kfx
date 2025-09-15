import io.github.hfhbd.kfx.soap.Header
import kotlinx.serialization.Serializable

@Serializable
data class MyHeader(val to: String) : Header
