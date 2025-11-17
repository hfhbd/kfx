package io.github.hfhbd.kfx.xsd.fir

import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.xsd.model.ComplexType
import io.github.hfhbd.kfx.xsd.model.Element
import io.github.hfhbd.kfx.xsd.model.SimpleType

interface XsdTransformer {
    operator fun invoke(simpleType: SimpleType, irClass: IRTree.Class): IRTree.Class

    operator fun invoke(complexType: ComplexType, irClass: IRTree.Class): IRTree.Class

    operator fun invoke(element: Element, irClass: IRTree.Class): IRTree.Class
}
