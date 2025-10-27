package io.github.hfhbd.kfx

import org.springframework.web.reactive.function.server.CoRouterFunctionDsl
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.contentTypeOrNull

fun CoRouterFunctionDsl.soap11(action: String) = RequestPredicates.headers {
    action == it.firstHeader("SOAPAction")
}

fun CoRouterFunctionDsl.soap12(action: String) = RequestPredicates.headers {
    action == it.contentTypeOrNull()?.getParameter("action")
}
