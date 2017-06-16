package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Voice
@JsonCreator constructor(
        @JsonProperty("file_id")
        var fileId: String? = null,
        @JsonProperty("duration")
        var duration: Int? = null,
        @JsonProperty("mime_type")
        var mimeType: String? = null,
        @JsonProperty("file_size")
        var fileSize: Int? = null
)