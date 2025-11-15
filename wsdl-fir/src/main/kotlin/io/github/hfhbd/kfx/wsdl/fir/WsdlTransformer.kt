package io.github.hfhbd.kfx.wsdl.fir

import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.wsdl.model.ComplexType
import io.github.hfhbd.kfx.wsdl.model.Element
import io.github.hfhbd.kfx.wsdl.model.SimpleType

interface WsdlTransformer {
    operator fun invoke(simpleType: SimpleType, irClass: IRTree.Class): IRTree.Class

    operator fun invoke(complexType: ComplexType, irClass: IRTree.Class): IRTree.Class

    operator fun invoke(element: Element, irClass: IRTree.Class): IRTree.Class
}
