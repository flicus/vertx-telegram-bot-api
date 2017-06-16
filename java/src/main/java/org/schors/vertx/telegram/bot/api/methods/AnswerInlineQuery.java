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

package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.inline.InlineQueryResult;

public class AnswerInlineQuery extends TelegramMethod {

    @JsonProperty("inline_query_id")
    private String inlineQueryId;
    private InlineQueryResult[] results;
    @JsonProperty("cache_time")
    private Integer cacheTime;
    @JsonProperty("is_personal")
    private Boolean isPersonal;
    @JsonProperty("next_offset")
    private String nextOffset;
    @JsonProperty("switch_pm_text")
    private String switchPmText;
    @JsonProperty("switch_pm_parameter")
    private String switchPmParameter;

    public AnswerInlineQuery() {
    }

    public AnswerInlineQuery(String inlineQueryId, InlineQueryResult[] results, Integer cacheTime, Boolean isPersonal, String nextOffset, String switchPmText, String switchPmParameter) {
        this.inlineQueryId = inlineQueryId;
        this.results = results;
        this.cacheTime = cacheTime;
        this.isPersonal = isPersonal;
        this.nextOffset = nextOffset;
        this.switchPmText = switchPmText;
        this.switchPmParameter = switchPmParameter;
    }

    public String getInlineQueryId() {
        return inlineQueryId;
    }

    public AnswerInlineQuery setInlineQueryId(String inlineQueryId) {
        this.inlineQueryId = inlineQueryId;
        return this;
    }

    public InlineQueryResult[] getResults() {
        return results;
    }

    public AnswerInlineQuery setResults(InlineQueryResult[] results) {
        this.results = results;
        return this;
    }

    public Integer getCacheTime() {
        return cacheTime;
    }

    public AnswerInlineQuery setCacheTime(Integer cacheTime) {
        this.cacheTime = cacheTime;
        return this;
    }

    public Boolean getPersonal() {
        return isPersonal;
    }

    public AnswerInlineQuery setPersonal(Boolean personal) {
        isPersonal = personal;
        return this;
    }

    public String getNextOffset() {
        return nextOffset;
    }

    public AnswerInlineQuery setNextOffset(String nextOffset) {
        this.nextOffset = nextOffset;
        return this;
    }

    public String getSwitchPmText() {
        return switchPmText;
    }

    public AnswerInlineQuery setSwitchPmText(String switchPmText) {
        this.switchPmText = switchPmText;
        return this;
    }

    public String getSwitchPmParameter() {
        return switchPmParameter;
    }

    public AnswerInlineQuery setSwitchPmParameter(String switchPmParameter) {
        this.switchPmParameter = switchPmParameter;
        return this;
    }

    @Override
    public String getMethod() {
        return "answerInlineQuery";
    }
}
