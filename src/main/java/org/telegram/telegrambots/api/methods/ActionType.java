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

package org.telegram.telegrambots.api.methods;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Types of actions for SendChatAction method.
 * @date 20 of June of 2016
 */
public enum ActionType {
    TYPING("typing"),
    RECORDVIDEO("record_video"),
    RECORDAUDIO("record_audio"),
    UPLOADPHOTO("upload_photo"),
    UPLOADVIDEO("upload_video"),
    UPLOADAUDIO("upload_audio"),
    UPLOADDOCUMENT("upload_document"),
    FINDLOCATION("find_location");

    private String text;

    ActionType(String text) {
        this.text = text;
    }

    /**
     * @param text text of the action
     * @return ActionType
     * @deprecated Added for backward compatibility, will be dropped in next mayor release
     */
    @Deprecated
    public static ActionType GetActionType(String text) throws IllegalArgumentException {
        switch (text) {
            case "typing":
                return TYPING;
            case "record_video":
                return RECORDVIDEO;
            case "record_audio":
                return RECORDAUDIO;
            case "upload_photo":
                return UPLOADPHOTO;
            case "upload_video":
                return UPLOADVIDEO;
            case "upload_audio":
                return UPLOADAUDIO;
            case "upload_document":
                return UPLOADDOCUMENT;
            case "find_location":
                return FINDLOCATION;
        }
        throw new IllegalArgumentException(text + " doesn't match any know ActionType");
    }

    @Override
    public String toString() {
        return text;
    }
}
