package kt.schors.vertx.telegram.bot.api.types.payments

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Invoice
@JsonCreator constructor(
        @JsonProperty("title")
        var title: String? = null,
        @JsonProperty("description")
        var description: String? = null,
        @JsonProperty("start_parameter")
        var startParameter: String? = null,
        @JsonProperty("currency")
        var currency: String? = null,
        @JsonProperty("total_amount")
        var totalAmount: Int? = null
)