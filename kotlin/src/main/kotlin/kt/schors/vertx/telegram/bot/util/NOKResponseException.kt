package kt.schors.vertx.telegram.bot.util

class NOKResponseException(val errorCode: String, val errorDescription: String) : Exception()