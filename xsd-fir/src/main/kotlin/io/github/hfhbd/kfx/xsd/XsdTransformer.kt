package io.github.hfhbd.kfx.xsd

import io.github.hfhbd.kfx.ir.IRTree

interface XsdTransformer {
    operator fun invoke(simpleType: SimpleType, irClass: IRTree.Class): IRTree.Class

    operator fun invoke(complexType: ComplexType, irClass: IRTree.Class): IRTree.Class

    operator fun invoke(element: Element, irClass: IRTree.Class): IRTree.Class
}
