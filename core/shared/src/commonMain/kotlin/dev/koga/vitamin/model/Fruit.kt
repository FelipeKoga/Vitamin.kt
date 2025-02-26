@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.model

import kotlinx.serialization.Serializable

@Serializable
@JsExport
data class Fruit(
    val name: String,
    val image: Image,
    val metadata: Metadata
) {
    @Serializable
    data class Image(
        val author: Author,
        val color: String,
        val url: String
    ) {
        @Serializable
        data class Author(
            val name: String,
            val url: String,
        )
    }

    @Serializable
    data class Metadata(
        val name: String,
        val value: String
    )

}
