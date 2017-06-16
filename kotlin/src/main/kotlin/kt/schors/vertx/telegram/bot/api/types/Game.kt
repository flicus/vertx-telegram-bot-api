package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Game
@JsonCreator constructor(
        @JsonProperty("title")
        var title: String? = null,
        @JsonProperty("description")
        var description: String? = null,
        @JsonProperty("photo")
        var photo: Array<PhotoSize>? = null,
        @JsonProperty("text")
        var text: String? = null,
        @JsonProperty("text_entities")
        var textEntities: Array<MessageEntity>? = null,
        @JsonProperty("animation")
        var animation: Animation? = null
)