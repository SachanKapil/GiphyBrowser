package com.giphybrowser.data.model.gif

import com.google.gson.annotations.SerializedName

data class GifBean(
    @SerializedName("analytics")
    var analytics: Analytics? = null,
    @SerializedName("analytics_response_payload")
    var analyticsResponsePayload: String? = null,
    @SerializedName("bitly_gif_url")
    var bitlyGifUrl: String? = null,
    @SerializedName("bitly_url")
    var bitlyUrl: String? = null,
    @SerializedName("content_url")
    var contentUrl: String? = null,
    @SerializedName("embed_url")
    var embedUrl: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("images")
    var images: Images? = null,
    @SerializedName("import_datetime")
    var importDatetime: String? = null,
    @SerializedName("is_sticker")
    var isSticker: Int? = null,
    @SerializedName("rating")
    var rating: String? = null,
    @SerializedName("slug")
    var slug: String? = null,
    @SerializedName("source")
    var source: String? = null,
    @SerializedName("source_post_url")
    var sourcePostUrl: String? = null,
    @SerializedName("source_tld")
    var sourceTld: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("trending_datetime")
    var trendingDatetime: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("user")
    var user: User? = null,
    @SerializedName("username")
    var username: String? = null
) {
    data class Analytics(
        @SerializedName("onclick")
        var onclick: Onclick? = null,
        @SerializedName("onload")
        var onload: Onload? = null,
        @SerializedName("onsent")
        var onsent: Onsent? = null
    ) {
        data class Onclick(
            @SerializedName("url")
            var url: String? = null
        )

        data class Onload(
            @SerializedName("url")
            var url: String? = null
        )

        data class Onsent(
            @SerializedName("url")
            var url: String? = null
        )
    }

    data class Images(
        @SerializedName("downsized")
        var downsized: Downsized? = null,
        @SerializedName("downsized_large")
        var downsizedLarge: DownsizedLarge? = null,
        @SerializedName("downsized_medium")
        var downsizedMedium: DownsizedMedium? = null,
        @SerializedName("downsized_small")
        var downsizedSmall: DownsizedSmall? = null,
        @SerializedName("downsized_still")
        var downsizedStill: DownsizedStill? = null,
        @SerializedName("fixed_height")
        var fixedHeight: FixedHeight? = null,
        @SerializedName("fixed_height_downsampled")
        var fixedHeightDownsampled: FixedHeightDownsampled? = null,
        @SerializedName("fixed_height_small")
        var fixedHeightSmall: FixedHeightSmall? = null,
        @SerializedName("fixed_height_small_still")
        var fixedHeightSmallStill: FixedHeightSmallStill? = null,
        @SerializedName("fixed_height_still")
        var fixedHeightStill: FixedHeightStill? = null,
        @SerializedName("fixed_width")
        var fixedWidth: FixedWidth? = null,
        @SerializedName("fixed_width_downsampled")
        var fixedWidthDownsampled: FixedWidthDownsampled? = null,
        @SerializedName("fixed_width_small")
        var fixedWidthSmall: FixedWidthSmall? = null,
        @SerializedName("fixed_width_small_still")
        var fixedWidthSmallStill: FixedWidthSmallStill? = null,
        @SerializedName("fixed_width_still")
        var fixedWidthStill: FixedWidthStill? = null,
        @SerializedName("looping")
        var looping: Looping? = null,
        @SerializedName("original")
        var original: Original? = null,
        @SerializedName("original_mp4")
        var originalMp4: OriginalMp4? = null,
        @SerializedName("original_still")
        var originalStill: OriginalStill? = null,
        @SerializedName("preview")
        var preview: Preview? = null,
        @SerializedName("preview_gif")
        var previewGif: PreviewGif? = null,
        @SerializedName("preview_webp")
        var previewWebp: PreviewWebp? = null,
        @SerializedName("480w_still")
        var wStill: WStill? = null
    ) {
        data class Downsized(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class DownsizedLarge(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class DownsizedMedium(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class DownsizedSmall(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("mp4")
            var mp4: String? = null,
            @SerializedName("mp4_size")
            var mp4Size: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class DownsizedStill(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedHeight(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("mp4")
            var mp4: String? = null,
            @SerializedName("mp4_size")
            var mp4Size: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("webp")
            var webp: String? = null,
            @SerializedName("webp_size")
            var webpSize: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedHeightDownsampled(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("webp")
            var webp: String? = null,
            @SerializedName("webp_size")
            var webpSize: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedHeightSmall(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("mp4")
            var mp4: String? = null,
            @SerializedName("mp4_size")
            var mp4Size: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("webp")
            var webp: String? = null,
            @SerializedName("webp_size")
            var webpSize: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedHeightSmallStill(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedHeightStill(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedWidth(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("mp4")
            var mp4: String? = null,
            @SerializedName("mp4_size")
            var mp4Size: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("webp")
            var webp: String? = null,
            @SerializedName("webp_size")
            var webpSize: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedWidthDownsampled(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("webp")
            var webp: String? = null,
            @SerializedName("webp_size")
            var webpSize: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedWidthSmall(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("mp4")
            var mp4: String? = null,
            @SerializedName("mp4_size")
            var mp4Size: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("webp")
            var webp: String? = null,
            @SerializedName("webp_size")
            var webpSize: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedWidthSmallStill(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class FixedWidthStill(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class Looping(
            @SerializedName("mp4")
            var mp4: String? = null,
            @SerializedName("mp4_size")
            var mp4Size: String? = null
        )

        data class Original(
            @SerializedName("frames")
            var frames: String? = null,
            @SerializedName("hash")
            var hash: String? = null,
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("mp4")
            var mp4: String? = null,
            @SerializedName("mp4_size")
            var mp4Size: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("webp")
            var webp: String? = null,
            @SerializedName("webp_size")
            var webpSize: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class OriginalMp4(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("mp4")
            var mp4: String? = null,
            @SerializedName("mp4_size")
            var mp4Size: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class OriginalStill(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class Preview(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("mp4")
            var mp4: String? = null,
            @SerializedName("mp4_size")
            var mp4Size: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class PreviewGif(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class PreviewWebp(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )

        data class WStill(
            @SerializedName("height")
            var height: String? = null,
            @SerializedName("size")
            var size: String? = null,
            @SerializedName("url")
            var url: String? = null,
            @SerializedName("width")
            var width: String? = null
        )
    }

    data class User(
        @SerializedName("avatar_url")
        var avatarUrl: String? = null,
        @SerializedName("banner_image")
        var bannerImage: String? = null,
        @SerializedName("banner_url")
        var bannerUrl: String? = null,
        @SerializedName("description")
        var description: String? = null,
        @SerializedName("display_name")
        var displayName: String? = null,
        @SerializedName("instagram_url")
        var instagramUrl: String? = null,
        @SerializedName("is_verified")
        var isVerified: Boolean? = null,
        @SerializedName("profile_url")
        var profileUrl: String? = null,
        @SerializedName("username")
        var username: String? = null,
        @SerializedName("website_url")
        var websiteUrl: String? = null
    )
}