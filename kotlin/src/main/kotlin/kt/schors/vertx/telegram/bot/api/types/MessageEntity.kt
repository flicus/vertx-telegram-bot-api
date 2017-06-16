package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class MessageEntity
@JsonCreator constructor(
        @JsonProperty("type")
        var type: String? = null,
        @JsonProperty("offset")
        var offset: Int? = null,
        @JsonProperty("length")
        var length: Int? = null,
        @JsonProperty("url")
        var url: String? = null,
        @JsonProperty("user")
        var user: User? = null
)