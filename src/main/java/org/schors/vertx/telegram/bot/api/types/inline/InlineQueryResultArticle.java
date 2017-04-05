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

package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMessageContent;

public class InlineQueryResultArticle extends InlineQueryResult {

    private String type = "article";
    private String id;
    private String title;
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    private String url;
    @JsonProperty("hide_url")
    private Boolean hideUrl;
    private String description;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    @JsonProperty("thumb_width")
    private Integer thumbWidth;
    @JsonProperty("thumb_height")
    private Integer thumbHeight;

    public InlineQueryResultArticle() {
        this.type = "article";
    }

    public InlineQueryResultArticle(String type, String id, String title, InputMessageContent inputMessageContent, InlineKeyboardMarkup replyMarkup, String url, Boolean hideUrl, String description, String thumbUrl, Integer thumbWidth, Integer thumbHeight) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.inputMessageContent = inputMessageContent;
        this.replyMarkup = replyMarkup;
        this.url = url;
        this.hideUrl = hideUrl;
        this.description = description;
        this.thumbUrl = thumbUrl;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
    }

    public String getType() {
        return type;
    }

    public InlineQueryResultArticle setType(String type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultArticle setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultArticle setTitle(String title) {
        this.title = title;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultArticle setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultArticle setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public InlineQueryResultArticle setUrl(String url) {
        this.url = url;
        return this;
    }

    public Boolean getHideUrl() {
        return hideUrl;
    }

    public InlineQueryResultArticle setHideUrl(Boolean hideUrl) {
        this.hideUrl = hideUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InlineQueryResultArticle setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public InlineQueryResultArticle setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public Integer getThumbWidth() {
        return thumbWidth;
    }

    public InlineQueryResultArticle setThumbWidth(Integer thumbWidth) {
        this.thumbWidth = thumbWidth;
        return this;
    }

    public Integer getThumbHeight() {
        return thumbHeight;
    }

    public InlineQueryResultArticle setThumbHeight(Integer thumbHeight) {
        this.thumbHeight = thumbHeight;
        return this;
    }
}
