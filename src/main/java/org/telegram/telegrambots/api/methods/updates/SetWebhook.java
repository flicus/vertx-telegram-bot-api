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

package org.telegram.telegrambots.api.methods.updates;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Use this method to specify a url and receive incoming updates via an outgoing webhook.
 * Whenever there is an update for the bot, we will send an HTTPS POST request to the specified url,
 * containing a JSON-serialized Update. In case of an unsuccessful request, we will give up after a
 * reasonable amount of attempts.
 * @date 20 of June of 2015
 */
public class SetWebhook {
    public static final String PATH = "setwebhook";

    public static final String URL_FIELD = "url";
    public static final String CERTIFICATE_FIELD = "certificate";
    private String url; ///< Optional. HTTPS url to send updates to. Use an empty string to remove webhook integration
    private String certificateFile; ///< Optional. Upload your public key certificate so that the root certificate in use can be checked

    public SetWebhook() {
        this.url = "";
    }

    public String getUrl() {
        return url;
    }

    public SetWebhook setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCertificateFile() {
        return certificateFile;
    }

    public SetWebhook setCertificateFile(String certificateFile) {
        this.certificateFile = certificateFile;
        return this;
    }

    @Override
    public String toString() {
        return "SetWebhook{" +
                "url='" + url + '\'' +
                ", certificateFile='" + certificateFile + '\'' +
                '}';
    }
}
