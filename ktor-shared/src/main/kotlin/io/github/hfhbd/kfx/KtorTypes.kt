package io.github.hfhbd.kfx

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import io.github.hfhbd.kfx.codegen.CodeGenTree

fun CodeGenTree.Type.toKtorPoetType(
    read: Boolean,
): TypeName = when (this) {
    CodeGenTree.Type.Builtin.FILE,
    CodeGenTree.Type.Builtin.BYTEARRAY,
    -> if (read) {
        ClassName("kotlinx.io", "Sink")
    } else {
        ClassName("kotlinx.io", "Source")
    }
    else -> toPoetType()
}
