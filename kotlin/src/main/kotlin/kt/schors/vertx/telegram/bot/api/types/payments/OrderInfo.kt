package kt.schors.vertx.telegram.bot.api.types.payments

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class OrderInfo
@JsonCreator constructor(
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("phone_number")
        var phoneNumber: String? = null,
        @JsonProperty("email")
        var email: String? = null,
        @JsonProperty("shipping_address")
        var shippingAddress: ShippingAddress? = null
)