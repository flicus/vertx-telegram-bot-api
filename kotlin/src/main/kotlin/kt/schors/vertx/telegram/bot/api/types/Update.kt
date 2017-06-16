/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2017 schors
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kt.schors.vertx.telegram.bot.api.types.inline.ChosenInlineResult
import kt.schors.vertx.telegram.bot.api.types.inline.InlineQuery
import kt.schors.vertx.telegram.bot.api.types.payments.PreCheckoutQuery
import kt.schors.vertx.telegram.bot.api.types.payments.ShippingQuery

data class Update
@JsonCreator constructor(
        @JsonProperty("update_id") val updateId: Int,
        @JsonProperty("message") val message: Message?,
        @JsonProperty("edited_message") val editedMessage: Message?,
        @JsonProperty("channel_post") val channelPost: Message?,
        @JsonProperty("edited_channel_post") val editedChannelPost: Message?,
        @JsonProperty("inline_query") val inlineQuery: InlineQuery?,
        @JsonProperty("chosen_inline_result") val chosenInlineResult: ChosenInlineResult?,
        @JsonProperty("callback_query") val callbackQuery: CallbackQuery?,
        @JsonProperty("shipping_query") val shippingQuery: ShippingQuery?,
        @JsonProperty("pre_checkout_query") val preCheckoutQuery: PreCheckoutQuery?)