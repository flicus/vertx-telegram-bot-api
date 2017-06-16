package kt.schors.vertx.telegram.bot.commands

import io.vertx.core.Handler
import kt.schors.vertx.telegram.bot.TelegramBot
import kt.schors.vertx.telegram.bot.api.types.Update

class CommandHandler(var bot: TelegramBot? = null) : Handler<Update> {

    override fun handle(event: Update?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}