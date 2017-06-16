package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class KeyboardButton @JsonCreator constructor(
        @JsonProperty("text") var text: String? = null,
        @JsonProperty("request_contact") var requestContact: Boolean? = null,
        @JsonProperty("request_location") var requestLocation: Boolean? = null
)