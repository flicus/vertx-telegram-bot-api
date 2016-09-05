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
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.api.interfaces.IBotApiObject;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief This object represents a file ready to be downloaded
 * @date 24 of June of 2015
 */
public class File implements IBotApiObject {
    private static final String FILE_ID = "file_id";
    private static final String FILE_SIZE_FIELD = "file_size";
    private static final String FILE_PATH_FIELD = "file_path";
    @JsonProperty(FILE_ID)
    private String fileId; ///< Unique identifier for this file
    @JsonProperty(FILE_SIZE_FIELD)
    private Integer fileSize; ///< Optional. File size, if known
    @JsonProperty(FILE_PATH_FIELD)
    private String filePath; ///< Optional. File path. Use https://api.telegram.org/file/bot<token>/<file_path> to get the file.

    public File() {
        super();
    }

    public File(JsonObject jsonObject) {
        super();
        this.fileId = jsonObject.getString(FILE_ID);
        if (jsonObject.containsKey(FILE_SIZE_FIELD)) {
            this.fileSize = jsonObject.getInteger(FILE_SIZE_FIELD);
        }
        if (jsonObject.containsKey(FILE_PATH_FIELD)) {
            this.filePath = jsonObject.getString(FILE_PATH_FIELD);
        }
    }

    public String getFileId() {
        return fileId;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(FILE_ID, fileId);
        if (fileSize != null) {
            gen.writeNumberField(FILE_SIZE_FIELD, fileSize);
        }
        if (filePath != null) {
            gen.writeStringField(FILE_PATH_FIELD, filePath);
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
        return "File{" +
                "fileId='" + fileId + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
