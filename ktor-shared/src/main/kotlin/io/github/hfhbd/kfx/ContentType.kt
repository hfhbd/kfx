package io.github.hfhbd.kfx

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.MemberName

fun ContentType.toKtor(): CodeBlock = when (this) {
    ContentType.ApplicationSoapXml -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Application",
            ),
            "Soap",
        ),
    )

    ContentType.ApplicationXml -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Application",
            ),
            "Xml",
        ),
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

    ContentType.ApplicationProblemJson -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Application",
            ),
            "ProblemJson",
        ),
    )

    ContentType.ApplicationProblemXml -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Application",
            ),
            "ProblemXml",
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
            "CSV",
        ),
    )

    ContentType.TextXml -> CodeBlock.of(
        "%M",
        MemberName(
            ClassName(
                "io.ktor.http",
                "ContentType",
                "Text",
            ),
            "Xml",
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
    ContentType.ApplicationXml -> true
    ContentType.ApplicationJson -> true
    ContentType.ApplicationProblemJson -> true
    ContentType.ApplicationProblemXml -> true
    ContentType.FormUrlEncoded -> false
    ContentType.MultipartFormData -> false
    ContentType.OctetStream -> false
    ContentType.TextPlain -> true
    ContentType.ApplicationZip -> false
    ContentType.TextCsv -> true
    ContentType.TextXml -> true
    is ContentType.Custom -> false
}
