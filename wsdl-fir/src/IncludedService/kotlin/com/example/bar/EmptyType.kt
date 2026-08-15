package com.example.bar

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "EmptyType",
  namespace = "http://example.com/bar",
)
@Serializable
@SerialName(value = "EmptyType")
public data object EmptyType
