package kt.schors.vertx.telegram.bot.api.types.payments

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class SuccessfulPayment
@JsonCreator constructor(
        @JsonProperty("currency")
        var currency: String? = null,
        @JsonProperty("total_amount")
        var totalAmount: Int? = null,
        @JsonProperty("invoice_payload")
        var invoicePayload: String? = null,
        @JsonProperty("shipping_option_id")
        var shippingOptionId: String? = null,
        @JsonProperty("order_info")
        var orderInfo: OrderInfo? = null,
        @JsonProperty("telegram_payment_charge_id")
        var telegramPaymentChargeId: String? = null,
        @JsonProperty("provider_payment_charge_id")
        var providerPaymentChargeId: String? = null
)