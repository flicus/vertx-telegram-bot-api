package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


data class ReplyKeyboardMarkup @JsonCreator constructor(
        @JsonProperty("keyboard")
        var keyboard: Array<Array<KeyboardButton>>? = null,
        @JsonProperty("resize_keyboard")
        var resizeKeyboard: Boolean? = null,
        @JsonProperty("one_time_keyboard")
        var oneTimeKeyboard: Boolean? = null,
        @JsonProperty("selective")
        var selective: Boolean? = null
) : Markup()