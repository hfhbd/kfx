package io.github.hfhbd.kfx.codegen

import io.github.hfhbd.kfx.ir.IRTree

interface CodeGenCreator {
    fun toCodeGen(ir: IRTree.Operation): CodeGenTree.Operation
    fun toCodeGen(ir: IRTree.Member, name: String): CodeGenTree.Member
    fun toCodeGen(ir: IRTree.Type): CodeGenTree.Type
    fun toCodeGen(ir: IRTree.Class): CodeGenTree.Class
    fun toCodeGen(ir: IRTree.Enum): CodeGenTree.Enum
    fun toCodeGen(ir: IRTree.NormalClass): CodeGenTree.NormalClass
    fun toCodeGen(ir: IRTree.Auth): CodeGenTree.Auth
    fun toCodeGen(ir: IRTree.ClassName): CodeGenTree.ClassName
}

fun CodeGenCreator.create(irTree: IRTree): CodeGenTree = CodeGenTree(
    classes = irTree.classes.mapTo(mutableSetOf()) {
        toCodeGen(it)
    },
    operations = irTree.operations.mapTo(mutableSetOf()) {
        toCodeGen(it)
    },
    auth = irTree.auth.mapTo(mutableSetOf()) { toCodeGen(it) },
)
