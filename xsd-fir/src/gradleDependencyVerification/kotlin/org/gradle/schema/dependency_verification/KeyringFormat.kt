package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
enum class KeyringFormat {
    @SerialName("armored")
    Armored,
    @SerialName("binary")
    Binary,
}
