package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "artifactType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class Artifact(
  val ignoredKeys: IgnoredKeys? = null,
  val pgp: Pgp? = null,
  val md5: Md5? = null,
  val sha1: Sha1? = null,
  val sha256: Sha256? = null,
  val sha512: Sha512? = null,
  val name: String,
)
