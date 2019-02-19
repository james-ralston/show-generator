package com.bottle.rocket.test.model

import java.util.*

data class Container(
        val id: UUID = UUID.randomUUID(),
        val name: String,
        val description: String,
        val assets: List<Asset>
)

abstract class Asset {
    abstract val id: UUID
    abstract val name: String
    abstract val url: String
    abstract val expirationDate: String

    abstract fun prettyPrint()
}

data class AdAsset(
        override val id: UUID,
        override val name: String,
        override val url: String,
        override val expirationDate: String,
        val productDescription: String

): Asset() {
    val type = AssetType.AD

    override fun prettyPrint() {
        print("""

            Ad Asset:
             id: $id
             name: $name
             url: $url
             expiration Date: $expirationDate,
             product Description: $productDescription
        """.trimIndent())
        println()
    }

    companion object {
        fun generate() = AdAsset(
                id = UUID.randomUUID(),
                name = "video name",
                url = "www.videoassset.com/ad1",
                expirationDate = "2019-04-15T15:53:00Z",
                productDescription = "Pretty cool item"
        )
    }
}

data class VideoAsset(
        override val id: UUID,
        override val name: String,
        override val url: String,
        override val expirationDate: String,
        val videoType: VideoType
): Asset() {
    val type = AssetType.VIDEO

    override fun prettyPrint() {
        print("""

            Video Asset:
             id: $id
             name: $name
             url: $url
             expirationDate: $expirationDate
             videoType: $videoType
        """.trimIndent())
        println()
    }

    companion object {
        fun generate() = VideoAsset(
                id = UUID.randomUUID(),
                name = "video name",
                url = "www.videoassset.com/video1",
                expirationDate = "2019-04-15T15:53:00Z",
                videoType = VideoType.CLIP

        )
    }
}

data class ImageAsset(
        override val id: UUID,
        override val name: String,
        override val url: String,
        override val expirationDate: String
): Asset() {
    val type = AssetType.IMAGE

    override fun prettyPrint() {
        print("""

            Image Asset:
             id: $id
             name: $name
             url: $url
             expirationDate: $expirationDate
        """.trimIndent())
        println()
    }

    companion object {
        fun generate() = ImageAsset(
                id = UUID.randomUUID(),
                name = "video name",
                url = "www.videoassset.com/image1",
                expirationDate = "2019-04-15T15:53:00Z"
        )
    }
}

enum class VideoType {
    MOVIE,
    EPISODE,
    CLIP
}

enum class AssetType {
    AD,
    VIDEO,
    IMAGE
}