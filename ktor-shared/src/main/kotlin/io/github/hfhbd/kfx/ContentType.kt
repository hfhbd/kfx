package io.github.hfhbd.kfx

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.MemberName

fun ContentType.toKtor(): CodeBlock = when (this) {
    ContentType.ApplicationSoapXml -> CodeBlock.of(
        "%T(%S, %S)",
        ClassName(
            "io.ktor.http",
            "ContentType",
        ),
        "application",
        "soap+xml",
    )

    ContentType.ApplicationJson -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Application",
            ),
            "Json",
        ),
    )

    ContentType.ApplicationZip -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Application",
            ),
            "Zip",
        ),
    )

    ContentType.FormUrlEncoded -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Application",
            ),
            "FormUrlEncoded",
        ),
    )

    ContentType.MultipartFormData -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "MultiPart",
            ),
            "FormData",
        ),
    )

    ContentType.OctetStream -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Application",
            ),
            "OctetStream",
        ),
    )

    ContentType.TextPlain -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Text",
            ),
            "Plain",
        ),
    )

    ContentType.TextCsv -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Text",
            ),
            "Plain",
        ),
    )

    is ContentType.Custom -> CodeBlock.of(
        "%T.parse(%S)",
        ClassName(
            "io.ktor.http",
            "ContentType",
        ),
        contentType,
    )
}

fun ContentType.supportedBySerialization() = when (this) {
    ContentType.ApplicationSoapXml -> true
    ContentType.ApplicationJson -> true
    ContentType.FormUrlEncoded -> false
    ContentType.MultipartFormData -> false
    ContentType.OctetStream -> false
    ContentType.TextPlain -> true
    ContentType.ApplicationZip -> false
    ContentType.TextCsv -> true
    is ContentType.Custom -> false
}
