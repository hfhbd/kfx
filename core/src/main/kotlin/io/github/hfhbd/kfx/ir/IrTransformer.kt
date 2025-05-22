package io.github.hfhbd.kfx.ir

fun interface IrTransformer {
    operator fun invoke(irTree: IRTree): IRTree
}
