/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2016 schors
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

package org.schors.vertx.telegram.bot.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Update {

    @JsonProperty("update_id")
    private Integer updateId;
    private Message message;
    @JsonProperty("edited_message")
    private Message editedMessage;
    @JsonProperty("channel_post")
    private Message channelPost;
    @JsonProperty("edited_channel_post")
    private Message editedChannelPost;
    @JsonProperty("inline_query")
    private InlineQuery inlineQuery;
    @JsonProperty("chosen_inline_result")
    private ChosenInlineResult chosenInlineResult;
    @JsonProperty("callback_query")
    private CallbackQuery callbackQuery;

    public Update() {
    }

    public Update(Integer updateId, Message message, Message editedMessage, Message channelPost, Message editedChannelPost, InlineQuery inlineQuery, ChosenInlineResult chosenInlineResult, CallbackQuery callbackQuery) {
        this.updateId = updateId;
        this.message = message;
        this.editedMessage = editedMessage;
        this.channelPost = channelPost;
        this.editedChannelPost = editedChannelPost;
        this.inlineQuery = inlineQuery;
        this.chosenInlineResult = chosenInlineResult;
        this.callbackQuery = callbackQuery;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public Update setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }

    public Message getMessage() {
        return message;
    }

    public Update setMessage(Message message) {
        this.message = message;
        return this;
    }

    public Message getEditedMessage() {
        return editedMessage;
    }

    public Update setEditedMessage(Message editedMessage) {
        this.editedMessage = editedMessage;
        return this;
    }

    public Message getChannelPost() {
        return channelPost;
    }

    public Update setChannelPost(Message channelPost) {
        this.channelPost = channelPost;
        return this;
    }

    public Message getEditedChannelPost() {
        return editedChannelPost;
    }

    public Update setEditedChannelPost(Message editedChannelPost) {
        this.editedChannelPost = editedChannelPost;
        return this;
    }

    public InlineQuery getInlineQuery() {
        return inlineQuery;
    }

    public Update setInlineQuery(InlineQuery inlineQuery) {
        this.inlineQuery = inlineQuery;
        return this;
    }

    public ChosenInlineResult getChosenInlineResult() {
        return chosenInlineResult;
    }

    public Update setChosenInlineResult(ChosenInlineResult chosenInlineResult) {
        this.chosenInlineResult = chosenInlineResult;
        return this;
    }

    public CallbackQuery getCallbackQuery() {
        return callbackQuery;
    }

    public Update setCallbackQuery(CallbackQuery callbackQuery) {
        this.callbackQuery = callbackQuery;
        return this;
    }

    public boolean hasMessage() {
        return message != null;
    }
}
