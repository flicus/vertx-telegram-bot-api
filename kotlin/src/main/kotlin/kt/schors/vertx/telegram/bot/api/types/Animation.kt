package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Animation
@JsonCreator constructor(
        @JsonProperty("file_id")
        var fileId: String? = null,
        @JsonProperty("thumb")
        var thumb: PhotoSize? = null,
        @JsonProperty("file_name")
        var fileName: String? = null,
        @JsonProperty("mime_type")
        var mimeType: String? = null,
        @JsonProperty("file_size")
        var fileSize: Int? = null
)