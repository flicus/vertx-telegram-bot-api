package kt.schors.vertx.telegram.bot.example

import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import kt.schors.vertx.telegram.bot.LongPollingReceiver
import kt.schors.vertx.telegram.bot.TelegramBot
import kt.schors.vertx.telegram.bot.TelegramOptions
import kt.schors.vertx.telegram.bot.api.methods.SendMessage
import kt.schors.vertx.telegram.bot.api.types.Message

class TestBot : AbstractVerticle() {

    var bot: TelegramBot? = null

    override fun start() {
        bot = TelegramBot(
                vertx = getVertx(),
                options = TelegramOptions(
                        botName = "test_bot",
                        botToken = "tokenPart1:tokenPart2"
                ),
                receiver = LongPollingReceiver(Handler { update ->
                    val name = update.message?.from?.firstName
                    bot?.send<Message>(SendMessage(chatId = update.message?.chat?.id.toString(), text = "Hello back, $name"))
                })
        )
        bot?.start()
    }
}