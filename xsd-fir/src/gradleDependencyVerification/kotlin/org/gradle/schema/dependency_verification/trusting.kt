package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "trusting",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data object trusting
