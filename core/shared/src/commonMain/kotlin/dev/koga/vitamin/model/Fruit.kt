@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@JsExport
data class Fruit(
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: Image,
    @SerialName("metadata")
    val metadata: List<Metadata>
)

@Serializable
@JsExport
data class Image(
    @SerialName("author")
    val author: Author,
    @SerialName("color")
    val color: String,
    @SerialName("url")
    val url: String
)

@Serializable
@JsExport
data class Author(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)

@Serializable
@JsExport
data class Metadata(
    @SerialName("name")
    val name: String,
    @SerialName("value")
    val value: String
)