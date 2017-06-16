package kt.schors.vertx.telegram.bot.api.methods

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kt.schors.vertx.telegram.bot.api.types.Markup
import kt.schors.vertx.telegram.bot.api.types.ParseMode

data class SendMessage
@JsonCreator constructor(
        @JsonProperty("chat_id") var chatId: String? = null,
        @JsonProperty("text") var text: String? = null,
        @JsonProperty("parse_mode") var parseMode: ParseMode? = null,
        @JsonProperty("disable_web_page_preview") var disableWebPagePreview: Boolean? = null,
        @JsonProperty("disable_notification") var disableNotification: Boolean? = null,
        @JsonProperty("reply_to_message_id") var replyToMessageId: Int? = null,
        @JsonProperty("reply_markup") var replyMarkup: Markup? = null
) : TelegramMethod("sendMessage")