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
import org.schors.vertx.telegram.bot.api.util.ParseMode;

public class InputTextMessageContent extends InputMessageContent {

    @JsonProperty("message_text")
    private String messageText;
    @JsonProperty("parse_mode")
    private ParseMode parseMode;
    @JsonProperty("disable_web_page_preview")
    private Boolean disableWebPagePreview;

    public InputTextMessageContent() {
    }

    public InputTextMessageContent(String messageText, ParseMode parseMode, Boolean disableWebPagePreview) {
        this.messageText = messageText;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
    }

    public String getMessageText() {
        return messageText;
    }

    public InputTextMessageContent setMessageText(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public ParseMode getParseMode() {
        return parseMode;
    }

    public InputTextMessageContent setParseMode(ParseMode parseMode) {
        this.parseMode = parseMode;
        return this;
    }

    public Boolean getDisableWebPagePreview() {
        return disableWebPagePreview;
    }

    public InputTextMessageContent setDisableWebPagePreview(Boolean disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview;
        return this;
    }
}
