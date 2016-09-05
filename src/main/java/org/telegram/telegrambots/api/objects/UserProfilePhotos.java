/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2016  schors
 *
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

package org.telegram.telegrambots.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.api.interfaces.IBotApiObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief This object represent a user's profile pictures.
 * @date 22 of June of 2015
 */
public class UserProfilePhotos implements IBotApiObject {

    private static final String TOTALCOUNT_FIELD = "total_count";
    private static final String PHOTOS_FIELD = "photos";
    @JsonProperty(TOTALCOUNT_FIELD)
    private Integer totalCount; ///< Total number of profile pictures the target user has
    @JsonProperty(PHOTOS_FIELD)
    private List<List<PhotoSize>> photos; ///< Requested profile pictures (in up to 4 sizes each)

    public UserProfilePhotos() {
        super();
    }

    public UserProfilePhotos(JsonObject jsonObject) {
        super();
        this.totalCount = jsonObject.getInteger(TOTALCOUNT_FIELD);
        if (totalCount > 0) {
            this.photos = new ArrayList<>();
            JsonArray photos = jsonObject.getJsonArray(PHOTOS_FIELD);
            for (int i = 0; i < photos.size(); i++) {
                JsonArray innerArray = photos.getJsonArray(i);
                List<PhotoSize> innerPhotos = new ArrayList<>();
                for (int j = 0; j < innerArray.size(); j++) {
                    innerPhotos.add(new PhotoSize(innerArray.getJsonObject(j)));
                }
                this.photos.add(innerPhotos);
            }
        }
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public List<List<PhotoSize>> getPhotos() {
        return photos;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField(TOTALCOUNT_FIELD, totalCount);
        if (totalCount > 0) {
            gen.writeArrayFieldStart(PHOTOS_FIELD);
            for (List<PhotoSize> photoSizeList : photos) {
                gen.writeStartArray();
                for (PhotoSize photoSize : photoSizeList) {
                    gen.writeObject(photoSize);
                }
                gen.writeEndArray();
            }
            gen.writeEndArray();
        }
        gen.writeEndObject();
        gen.flush();
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(gen, serializers);
    }

    @Override
    public String toString() {
        return "UserProfilePhotos{" +
                "totalCount=" + totalCount +
                ", photos=" + photos +
                '}';
    }
}
