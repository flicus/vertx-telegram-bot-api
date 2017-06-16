package kt.schors.vertx.telegram.bot.api.types.payments

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ShippingAddress
@JsonCreator constructor(
        @JsonProperty("country_code")
        var countryCode: String? = null,
        @JsonProperty("state")
        var state: String? = null,
        @JsonProperty("city")
        var city: String? = null,
        @JsonProperty("street_line1")
        var streetLine1: String? = null,
        @JsonProperty("street_line2")
        var streetLine2: String? = null,
        @JsonProperty("post_code")
        var postCode: String? = null
)