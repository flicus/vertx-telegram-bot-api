package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Venue
@JsonCreator constructor(
        @JsonProperty("location")
        var location: Location? = null,
        @JsonProperty("title")
        var title: String? = null,
        @JsonProperty("address")
        var address: String? = null,
        @JsonProperty("foursquare_id")
        var foursquareId: String? = null
)