package kt.schors.vertx.telegram.bot.api.methods

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class GetUpdates
@JsonCreator constructor(
        @JsonProperty("offset")
        var offset: Int? = null,
        @JsonProperty("limit")
        var limit: Int? = null,
        @JsonProperty("timeout")
        var timeout: Int? = null,
        @JsonProperty("allowed_updates")
        var allowedUpdates: Array<String>? = null
) : TelegramMethod("getUpdates")