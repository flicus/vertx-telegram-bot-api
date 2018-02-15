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

package org.schors.vertx.telegram.bot.api.types.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.MessageEntity;
import org.schors.vertx.telegram.bot.api.types.PhotoSize;

public class Game {

    private String title;
    private String description;
    private PhotoSize[] photo;
    private String text;
    @JsonProperty("text_entities")
    private MessageEntity[] textEntities;
    private Animation animation;

    public Game() {
    }

    public Game(String title, String description, PhotoSize[] photo, String text, MessageEntity[] textEntities, Animation animation) {
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.text = text;
        this.textEntities = textEntities;
        this.animation = animation;
    }

    public String getTitle() {
        return title;
    }

    public Game setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Game setDescription(String description) {
        this.description = description;
        return this;
    }

    public PhotoSize[] getPhoto() {
        return photo;
    }

    public Game setPhoto(PhotoSize[] photo) {
        this.photo = photo;
        return this;
    }

    public String getText() {
        return text;
    }

    public Game setText(String text) {
        this.text = text;
        return this;
    }

    public MessageEntity[] getTextEntities() {
        return textEntities;
    }

    public Game setTextEntities(MessageEntity[] textEntities) {
        this.textEntities = textEntities;
        return this;
    }

    public Animation getAnimation() {
        return animation;
    }

    public Game setAnimation(Animation animation) {
        this.animation = animation;
        return this;
    }
}
