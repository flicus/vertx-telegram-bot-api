package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Sticker
@JsonCreator constructor(
        @JsonProperty("file_id")
        var fileId: String? = null,
        @JsonProperty("width")
        var width: Int? = null,
        @JsonProperty("height")
        var height: Int? = null,
        @JsonProperty("thumb")
        var thumb: PhotoSize? = null,
        @JsonProperty("emoji")
        var emoji: String? = null,
        @JsonProperty("file_size")
        var fileSize: Int? = null
)