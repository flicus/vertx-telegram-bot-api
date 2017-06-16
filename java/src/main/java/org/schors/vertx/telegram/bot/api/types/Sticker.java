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

public class Sticker {

    @JsonProperty("file_id")
    private String fileId;
    private Integer width;
    private Integer height;
    private PhotoSize thumb;
    private String emoji;
    @JsonProperty("file_size")
    private Integer fileSize;

    public Sticker() {
    }

    public Sticker(String fileId, Integer width, Integer height, PhotoSize thumb, String emoji, Integer fileSize) {
        this.fileId = fileId;
        this.width = width;
        this.height = height;
        this.thumb = thumb;
        this.emoji = emoji;
        this.fileSize = fileSize;
    }

    public String getFileId() {
        return fileId;
    }

    public Sticker setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public Sticker setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public Sticker setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public PhotoSize getThumb() {
        return thumb;
    }

    public Sticker setThumb(PhotoSize thumb) {
        this.thumb = thumb;
        return this;
    }

    public String getEmoji() {
        return emoji;
    }

    public Sticker setEmoji(String emoji) {
        this.emoji = emoji;
        return this;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public Sticker setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
        return this;
    }
}
