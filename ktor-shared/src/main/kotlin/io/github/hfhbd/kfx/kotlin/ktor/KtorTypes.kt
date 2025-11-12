package io.github.hfhbd.kfx.kotlin.ktor

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.TypeName
import io.github.hfhbd.kfx.StatusCode
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.kotlin.toPoetType

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

fun StatusCode.toHttpCode(): MemberName {
    val className = ClassName("io.ktor.http", "HttpStatusCode", "Companion")
    return when (this) {
        StatusCode.OK -> MemberName(className, "OK")
        StatusCode.Created -> MemberName(className, "Created")
        StatusCode.Accepted -> MemberName(className, "Accepted")
        StatusCode.NoContent -> MemberName(className, "NoContent")
        StatusCode.BadRequest -> MemberName(className, "BadRequest")
        StatusCode.NotFound -> MemberName(className, "NotFound")
        StatusCode.Unauthorized -> MemberName(className, "Unauthorized")
        StatusCode.Forbidden -> MemberName(className, "Forbidden")
        StatusCode.NotAcceptable -> MemberName(className, "NotAcceptable")
        StatusCode.Conflict -> MemberName(className, "Conflict")
        StatusCode.LengthRequired -> MemberName(className, "LengthRequired")
        StatusCode.ContentTooLarge -> MemberName(className, "ContentTooLarge")
        StatusCode.UnprocessableEntity -> MemberName(className, "UnprocessableEntity")
        StatusCode.TooManyRequests -> MemberName(className, "TooManyRequests")
        StatusCode.InternalServerError -> MemberName(className, "InternalServerError")
    }
}
