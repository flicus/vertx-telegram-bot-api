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

public class File {

    @JsonProperty("file_id")
    private String fileId;
    @JsonProperty("file_size")
    private Integer fileSize;
    @JsonProperty("file_path")
    private String filePath;

    public File() {
    }

    public File(String fileId, Integer fileSize, String filePath) {
        this.fileId = fileId;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    public String getFileId() {
        return fileId;
    }

    public File setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public File setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public File setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
}
