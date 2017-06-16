package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class VideoNote
@JsonCreator constructor(
        @JsonProperty("file_id")
        var fileId: String? = null,
        @JsonProperty("length")
        var length: Int? = null,
        @JsonProperty("duration")
        var duration: Int? = null,
        @JsonProperty("thumb")
        var thumb: PhotoSize? = null,
        @JsonProperty("file_size")
        var fileSize: Int? = null
)