package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Location
@JsonCreator constructor(
        @JsonProperty("longitude")
        var longitude: Double? = null,
        @JsonProperty("latitude")
        var latitude: Double? = null
)