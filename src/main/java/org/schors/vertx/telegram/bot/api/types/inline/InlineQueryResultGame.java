package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineQueryResultGame extends InlineQueryResult {
    private Type type;
    private String id;
    @JsonProperty("game_short_name")
    private String gameShortName;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    public InlineQueryResultGame() {
        this.type = Type.game;
    }

    public InlineQueryResultGame(Type type, String id, String gameShortName, InlineKeyboardMarkup replyMarkup) {
        this.type = type;
        this.id = id;
        this.gameShortName = gameShortName;
        this.replyMarkup = replyMarkup;
    }

    public Type getType() {
        return type;
    }

    public InlineQueryResultGame setType(Type type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultGame setId(String id) {
        this.id = id;
        return this;
    }

    public String getGameShortName() {
        return gameShortName;
    }

    public InlineQueryResultGame setGameShortName(String gameShortName) {
        this.gameShortName = gameShortName;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultGame setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }
}
