package io.github.hfhbd.kfx.wsdl.fir

import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.wsdl.model.Operation
import io.github.hfhbd.kfx.wsdl.model.WSDL

interface WsdlTransformer {
    operator fun invoke(operation: Operation, wsdl: WSDL, irOperation: IRTree.Operation): IRTree.Operation = irOperation
}
