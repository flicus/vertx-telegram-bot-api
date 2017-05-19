/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2017 schors
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

package org.schors.vertx.telegram.bot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.json.JsonObject;
import org.apache.log4j.Logger;
import org.schors.vertx.telegram.bot.api.methods.*;
import org.schors.vertx.telegram.bot.api.types.*;
import org.schors.vertx.telegram.bot.util.MultipartHelper;
import org.schors.vertx.telegram.bot.util.NOKResponseException;
import org.schors.vertx.telegram.bot.util.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TelegramBot {

    private static final Logger log = Logger.getLogger(TelegramBot.class);

    private Vertx vertx;
    private HttpClient client;
    private TelegramOptions botOptions;
    private UpdateReceiver receiver;
    private Map<String, Object> facilities = new HashMap<>();
    private ObjectMapper mapper = new ObjectMapper();

    public TelegramBot(Vertx vertx, TelegramOptions options) {
        this.vertx = vertx;
        this.botOptions = options;

        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        HttpClientOptions httpOptions = new HttpClientOptions()
                .setSsl(true)
                .setTrustAll(true)
                .setIdleTimeout(this.getOptions().getPollingTimeout())
                .setMaxPoolSize(this.getOptions().getMaxConnections())
                .setDefaultHost(Util.BASEHOST)
                .setDefaultPort(443)
                .setLogActivity(true);

        if (options.getProxyOptions() != null)
            httpOptions.setProxyOptions(options.getProxyOptions());

        client = vertx.createHttpClient(httpOptions);
    }

    public static TelegramBot create(Vertx vertx) {
        return create(vertx, new TelegramOptions());
    }

    public static TelegramBot create(Vertx vertx, TelegramOptions options) {
        return new TelegramBot(vertx, options);
    }

    public TelegramBot receiver(UpdateReceiver updateReceiver) {
        this.receiver = updateReceiver;
        updateReceiver.bot(this);
        return this;
    }

    public TelegramBot start() {
        receiver.start();
        return this;
    }

    public TelegramBot stop() {
        receiver.stop();
        return this;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public TelegramOptions getOptions() {
        return botOptions;
    }

    public TelegramBot addFacility(String key, Object facility) {
        facilities.put(key, facility);
        return this;
    }

    public Object getFacility(String key) {
        return facilities.get(key);
    }

    public Map<String, Object> getFacilities() {
        return facilities;
    }

    private <T> void send(TelegramMethod message, Handler<AsyncResult<T>> handler) {
        String toSend = null;
        try {
            toSend = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error("### Unable to serialize to JSON", e);
            if (handler != null) {
                handler.handle(Util.makeAsyncResult(null, e));
            }
        }
        if (toSend != null)
            client
                    .post(Util.BASEURL + getOptions().getBotToken() + "/" + message.getMethod())
                    .handler(response -> {
                        response.bodyHandler(event -> {
                            if (handler != null) {
                                JsonObject json = event.toJsonObject();
                                if (!json.getBoolean(Util.R_OK)) {
                                    log.warn("### Unsuccessful response: " + json.toString());
                                    String errorCode = json.getString(Util.R_ERRORCODE);
                                    String errorDescription = json.getString(Util.R_ERRORDESCRIPTION);
                                    handler.handle(Util.makeAsyncResult(null, new NOKResponseException(errorCode, errorDescription)));
                                } else {
                                    JsonObject jsonMessage = json.getJsonObject(Util.R_RESULT);
                                    T resultMessage = null;
                                    try {
                                        resultMessage = mapper.readValue(jsonMessage.toString(), (Class<T>) resultMessage.getClass());
                                        handler.handle(Util.makeAsyncResult(resultMessage, null));
                                    } catch (IOException e) {
                                        handler.handle(Util.makeAsyncResult(null, e));
                                    }
                                }
                            }
                        });
                    })
                    .exceptionHandler(e -> {
                        if (handler != null) {
                            handler.handle(Util.makeAsyncResult(null, e));
                        }
                    })
                    .setTimeout(75000)
                    .putHeader("Content-Type", "application/json")
                    .end(toSend, "UTF-8");
    }

    public void sendMessage(SendMessage message) {
        send(message, null);
    }

    public void sendMessage(SendMessage message, Handler<AsyncResult<Message>> handler) {
        send(message, handler);
    }

    public void sendChatAction(SendChatAction chatAction) {
        send(chatAction, null);
    }

    public void sendChatAction(SendChatAction chatAction, Handler<AsyncResult<Boolean>> handler) {
        send(chatAction, handler);
    }

    public void sendAnswerInlineQuery(AnswerInlineQuery answerInlineQuery) {
        send(answerInlineQuery, null);
    }

    public void sendAnswerInlineQuery(AnswerInlineQuery answerInlineQuery, Handler<AsyncResult<Boolean>> handler) {
        send(answerInlineQuery, handler);
    }

    public void forwardMessage(ForwardMessage message) {
        send(message, null);
    }

    public void forwardMessage(ForwardMessage message, Handler<AsyncResult<Message>> handler) {
        send(message, handler);
    }

    public void sendLocation(SendLocation sendLocation) {
        send(sendLocation, null);
    }

    public void sendLocation(SendLocation sendLocation, Handler<AsyncResult<Message>> handler) {
        send(sendLocation, handler);
    }

    public void sendVenue(SendVenue sendVenue) {
        send(sendVenue, null);
    }

    public void sendVenue(SendVenue sendVenue, Handler<AsyncResult<Message>> handler) {
        send(sendVenue, handler);
    }

    public void sendContact(SendContact sendContact) {
        send(sendContact, null);
    }

    public void sendContact(SendContact sendContact, Handler<AsyncResult<Message>> handler) {
        send(sendContact, handler);
    }

    public void getUserProfilePhotos(GetUserProfilePhotos getUserProfilePhotos) {
        send(getUserProfilePhotos, null);
    }

    public void getUserProfilePhotos(GetUserProfilePhotos getUserProfilePhotos, Handler<AsyncResult<UserProfilePhotos>> handler) {
        send(getUserProfilePhotos, handler);
    }

    public void getMe(GetMe getMe) {
        send(getMe, null);
    }

    public void getMe(GetMe getMe, Handler<AsyncResult<User>> handler) {
        send(getMe, handler);
    }

    public void getFile(GetFile getFile) {
        send(getFile, null);
    }

    public void getFile(GetFile getFile, Handler<AsyncResult<File>> handler) {
        send(getFile, handler);
    }

    public void kickChatMember(KickChatMember kickChatMember) {
        send(kickChatMember, null);
    }

    public void kickChatMember(KickChatMember kickChatMember, Handler<AsyncResult<Boolean>> handler) {
        send(kickChatMember, handler);
    }

    public void leaveChat(LeaveChat leaveChat) {
        send(leaveChat, null);
    }

    public void leaveChat(LeaveChat leaveChat, Handler<AsyncResult<Boolean>> handler) {
        send(leaveChat, handler);
    }

    public void unbanChatMember(UnbanChatMember unbanChatMember) {
        send(unbanChatMember, null);
    }

    public void unbanChatMember(UnbanChatMember unbanChatMember, Handler<AsyncResult<Boolean>> handler) {
        send(unbanChatMember, handler);
    }

    public void getChat(GetChat getChat) {
        send(getChat, null);
    }

    public void getChat(GetChat getChat, Handler<AsyncResult<Chat>> handler) {
        send(getChat, handler);
    }

    public void getChatAdministrators(GetChatAdministrators getChatAdministrators) {
        send(getChatAdministrators, null);
    }

    public void getChatAdministrators(GetChatAdministrators getChatAdministrators, Handler<AsyncResult<ChatMember[]>> handler) {
        send(getChatAdministrators, handler);
    }

    public void getChatMembersCount(GetChatMembersCount getChatMembersCount) {
        send(getChatMembersCount, null);
    }

    public void getChatMembersCount(GetChatMembersCount getChatMembersCount, Handler<AsyncResult<Integer>> handler) {
        send(getChatMembersCount, handler);
    }

    public void getChatMember(GetChatMember getChatMember) {
        send(getChatMember, null);
    }

    public void getChatMember(GetChatMember getChatMember, Handler<AsyncResult<ChatMember>> handler) {
        send(getChatMember, handler);
    }

    public void editMessageText(EditMessageText editMessageText, Handler<AsyncResult<Message>> handler) {   //todo may return true
        send(editMessageText, handler);
    }

    public void editMessageText(EditMessageText editMessageText) {
        send(editMessageText, null);
    }

    public void editMessageCaption(EditMessageCaption editMessageCaption) {
        send(editMessageCaption, null);
    }

    public void editMessageCaption(EditMessageCaption editMessageCaption, Handler<AsyncResult<Message>> handler) {
        send(editMessageCaption, handler);
    }

    public void editMessageReplyMarkup(EditMessageReplyMarkup editMessageReplyMarkup) {
        send(editMessageReplyMarkup, null);
    }

    public void editMessageReplyMarkup(EditMessageReplyMarkup editMessageReplyMarkup, Handler<AsyncResult<Message>> handler) {
        send(editMessageReplyMarkup, handler);
    }

    public void deleteMessage(DeleteMessage deleteMessage) {
        send(deleteMessage, null);
    }

    public void deleteMessage(DeleteMessage deleteMessage, Handler<AsyncResult<Boolean>> handler) {
        send(deleteMessage, handler);
    }

    public void sendVideoNote(SendVideoNote sendVideoNote) {
        sendVideoNote(sendVideoNote, null);
    }

    public void sendVideoNote(SendVideoNote sendVideoNote, Handler<AsyncResult<Message>> handler) {
        vertx.executeBlocking(future -> {
            HttpClientRequest request = createRequest(sendVideoNote, future);
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", sendVideoNote.getChatId())
                    .putTextBody("reply_to_message_id", sendVideoNote.getReplyToMessageId())
                    .putTextBody("duration", sendVideoNote.getDuration())
                    .putTextBody("length", sendVideoNote.getLength())
                    .putTextBody("disable_notification", sendVideoNote.isDisableNotification())
                    .putTextBody("reply_markup", sendVideoNote.getReplyMarkup());
            if (sendVideoNote.getFile() != null) {
                multipartHelper
                        .putBinaryBody("video_note", sendVideoNote.getFile(), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (sendVideoNote.getLocalFilePath() != null) {
                multipartHelper
                        .putBinaryBody("video_note", new java.io.File(sendVideoNote.getLocalFilePath()), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (sendVideoNote.getStream() != null) {
                multipartHelper
                        .putBinaryBody("video_note", sendVideoNote.getStream(), "application/octet-stream", sendVideoNote.getVideoNote(), event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (sendVideoNote.getVideoNote() != null) {
                multipartHelper.putTextBody("video_note", sendVideoNote.getVideoNote());
            }
        }, event -> {
            if (handler != null) {
                if (event.succeeded()) {
                    handler.handle(Util.makeAsyncResult((Message) event.result(), null));
                } else {
                    handler.handle(Util.makeAsyncResult(null, event.cause()));
                }
            }
        });
    }

    public void sendDocument(SendDocument document) {
        sendDocument(document, null);
    }

    public void sendDocument(SendDocument document, Handler<AsyncResult<Message>> handler) {
        vertx.executeBlocking(future -> {
            HttpClientRequest request = createRequest(document, future);
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", document.getChatId())
                    .putTextBody("reply_to_message_id", document.getReplyToMessageId())
                    .putTextBody("caption", document.getCaption())
                    .putTextBody("disable_notification", document.isDisableNotification())
                    .putTextBody("reply_markup", document.getReplyMarkup());
            if (document.getFile() != null) {
                multipartHelper
                        .putBinaryBody("document", document.getFile(), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (document.getLocalFilePath() != null) {
                multipartHelper
                        .putBinaryBody("document", new java.io.File(document.getLocalFilePath()), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (document.getStream() != null) {
                multipartHelper
                        .putBinaryBody("document", document.getStream(), "application/octet-stream", document.getDocument(), event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (document.getDocument() != null) {
                multipartHelper.putTextBody("document", document.getDocument());
            }
        }, event -> {
            if (handler != null) {
                if (event.succeeded()) {
                    handler.handle(Util.makeAsyncResult((Message) event.result(), null));
                } else {
                    handler.handle(Util.makeAsyncResult(null, event.cause()));
                }
            }
        });
    }

    private HttpClientRequest createRequest(TelegramMethod method, Future<Object> future) {
        return client
                .post(Util.BASEURL + getOptions().getBotToken() + "/" + method.getMethod())
                .handler(response -> {
                    response.bodyHandler(event -> {
                        JsonObject json = event.toJsonObject();
                        if (!json.getBoolean(Util.R_OK)) {
                            log.warn("### Unsuccessful response: " + json.toString());
                        } else {
                            JsonObject jsonMessage = json.getJsonObject(Util.R_RESULT);
                            Message message = null;
                            try {
                                message = mapper.readValue(jsonMessage.toString(), Message.class);
                                future.complete(message);
                            } catch (IOException e) {
                                future.fail(e);
                            }
                            future.complete(message);
                        }
                    });
                })
                .exceptionHandler(e -> {
                    future.fail(e);
                })
                .setTimeout(75000)
                .setChunked(true);
    }

    public void sendPhoto(SendPhoto photo, Handler<AsyncResult<Message>> handler) {
        vertx.executeBlocking(future -> {
            HttpClientRequest request = createRequest(photo, future);
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", photo.getChatId())
                    .putTextBody("reply_to_message_id", photo.getReplyToMessageId())
                    .putTextBody("caption", photo.getCaption())
                    .putTextBody("disable_notification", photo.isDisableNotification())
                    .putTextBody("reply_markup", photo.getReplyMarkup());
            if (photo.getFile() != null) {
                multipartHelper
                        .putBinaryBody("photo", photo.getFile(), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (photo.getLocalFilePath() != null) {
                multipartHelper
                        .putBinaryBody("photo", new java.io.File(photo.getLocalFilePath()), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (photo.getStream() != null) {
                multipartHelper
                        .putBinaryBody("photo", photo.getStream(), "application/octet-stream", photo.getPhoto(), event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (photo.getPhoto() != null) {
                multipartHelper.putTextBody("photo", photo.getPhoto());
            }
        }, event -> {
            if (handler != null) {
                if (event.succeeded()) {
                    handler.handle(Util.makeAsyncResult((Message) event.result(), null));
                } else {
                    handler.handle(Util.makeAsyncResult(null, event.cause()));
                }
            }
        });
    }

    public void sendPhoto(SendPhoto photo) {
        sendPhoto(photo, null);
    }

    public void sendAudio(SendAudio audio, Handler<AsyncResult<Message>> handler) {
        vertx.executeBlocking(future -> {
            HttpClientRequest request = createRequest(audio, future);
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", audio.getChatId())
                    .putTextBody("reply_to_message_id", audio.getReplyToMessageId())
                    .putTextBody("caption", audio.getCaption())
                    .putTextBody("disable_notification", audio.isDisableNotification())
                    .putTextBody("reply_markup", audio.getReplyMarkup())
                    .putTextBody("duration", audio.getDuration())
                    .putTextBody("performer", audio.getPerformer())
                    .putTextBody("title", audio.getTitle());
            if (audio.getFile() != null) {
                multipartHelper
                        .putBinaryBody("audio", audio.getFile(), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (audio.getLocalFilePath() != null) {
                multipartHelper
                        .putBinaryBody("audio", new java.io.File(audio.getLocalFilePath()), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (audio.getStream() != null) {
                multipartHelper
                        .putBinaryBody("audio", audio.getStream(), "application/octet-stream", audio.getAudio(), event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (audio.getAudio() != null) {
                multipartHelper.putTextBody("audio", audio.getAudio());
            }
        }, event -> {
            if (handler != null) {
                if (event.succeeded()) {
                    handler.handle(Util.makeAsyncResult((Message) event.result(), null));
                } else {
                    handler.handle(Util.makeAsyncResult(null, event.cause()));
                }
            }
        });
    }

    public void sendAudio(SendAudio audio) {
        sendAudio(audio, null);
    }

    public void sendSticker(SendSticker sticker, Handler<AsyncResult<Message>> handler) {
        vertx.executeBlocking(future -> {
            HttpClientRequest request = createRequest(sticker, future);
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", sticker.getChatId())
                    .putTextBody("reply_to_message_id", sticker.getReplyToMessageId())
                    .putTextBody("disable_notification", sticker.isDisableNotification())
                    .putTextBody("reply_markup", sticker.getReplyMarkup());
            if (sticker.getFile() != null) {
                multipartHelper
                        .putBinaryBody("sticker", sticker.getFile(), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (sticker.getLocalFilePath() != null) {
                multipartHelper
                        .putBinaryBody("sticker", new java.io.File(sticker.getLocalFilePath()), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (sticker.getStream() != null) {
                multipartHelper
                        .putBinaryBody("sticker", sticker.getStream(), "application/octet-stream", sticker.getSticker(), event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (sticker.getSticker() != null) {
                multipartHelper.putTextBody("sticker", sticker.getSticker());
            }
        }, event -> {
            if (handler != null) {
                if (event.succeeded()) {
                    handler.handle(Util.makeAsyncResult((Message) event.result(), null));
                } else {
                    handler.handle(Util.makeAsyncResult(null, event.cause()));
                }
            }
        });
    }

    public void sendSticker(SendSticker sticker) {
        sendSticker(sticker, null);
    }

    public void sendVideo(SendVideo video, Handler<AsyncResult<Message>> handler) {
        vertx.executeBlocking(future -> {
            HttpClientRequest request = createRequest(video, future);
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", video.getChatId())
                    .putTextBody("reply_to_message_id", video.getReplyToMessageId())
                    .putTextBody("disable_notification", video.isDisableNotification())
                    .putTextBody("reply_markup", video.getReplyMarkup())
                    .putTextBody("duration", video.getDuration())
                    .putTextBody("caption", video.getCaption())
                    .putTextBody("width", video.getHeight())
                    .putTextBody("height", video.getWidth());
            if (video.getFile() != null) {
                multipartHelper
                        .putBinaryBody("video", video.getFile(), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (video.getLocalFilePath() != null) {
                multipartHelper
                        .putBinaryBody("video", new java.io.File(video.getLocalFilePath()), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (video.getStream() != null) {
                multipartHelper
                        .putBinaryBody("video", video.getStream(), "application/octet-stream", video.getVideo(), event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (video.getVideo() != null) {
                multipartHelper.putTextBody("video", video.getVideo());
            }
        }, event -> {
            if (handler != null) {
                if (event.succeeded()) {
                    handler.handle(Util.makeAsyncResult((Message) event.result(), null));
                } else {
                    handler.handle(Util.makeAsyncResult(null, event.cause()));
                }
            }
        });
    }

    public void sendVideo(SendVideo video) {
        sendVideo(video, null);
    }

    public void sendVoice(SendVoice voice, Handler<AsyncResult<Message>> handler) {
        vertx.executeBlocking(future -> {
            HttpClientRequest request = createRequest(voice, future);
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", voice.getChatId())
                    .putTextBody("reply_to_message_id", voice.getReplyToMessageId())
                    .putTextBody("caption", voice.getCaption())
                    .putTextBody("disable_notification", voice.isDisableNotification())
                    .putTextBody("reply_markup", voice.getReplyMarkup())
                    .putTextBody("duration", voice.getDuration());
            if (voice.getFile() != null) {
                multipartHelper
                        .putBinaryBody("voice", voice.getFile(), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (voice.getLocalFilePath() != null) {
                multipartHelper
                        .putBinaryBody("voice", new java.io.File(voice.getLocalFilePath()), "application/octet-stream", event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (voice.getStream() != null) {
                multipartHelper
                        .putBinaryBody("voice", voice.getStream(), "application/octet-stream", voice.getVoice(), event -> {
                            multipartHelper.stop();
                            request.end();
                        });
            } else if (voice.getVoice() != null) {
                multipartHelper.putTextBody("voice", voice.getVoice());
            }
        }, event -> {
            if (handler != null) {
                if (event.succeeded()) {
                    handler.handle(Util.makeAsyncResult((Message) event.result(), null));
                } else {
                    handler.handle(Util.makeAsyncResult(null, event.cause()));
                }
            }
        });
    }

    public void sendVoice(SendVoice voice) {
        sendVoice(voice, null);
    }

    public void sendInvoice(SendInvoice sendInvoice, Handler<AsyncResult<Message>> handler) {
        send(sendInvoice, handler);
    }

    public void sendInvoice(SendInvoice sendInvoice) {
        sendInvoice(sendInvoice, null);
    }

    public void answerShippingQuery(AnswerShippingQuery answerShippingQuery, Handler<AsyncResult<Boolean>> handler) {
        send(answerShippingQuery, handler);
    }

    public void answerShippingQuery(AnswerShippingQuery answerShippingQuery) {
        send(answerShippingQuery, null);
    }

    public void answerPreCheckoutQuery(AnswerPreCheckoutQuery answerPreCheckoutQuery, Handler<AsyncResult<Boolean>> handler) {
        send(answerPreCheckoutQuery, handler);
    }

    public void answerPreCheckoutQuery(AnswerPreCheckoutQuery answerPreCheckoutQuery) {
        send(answerPreCheckoutQuery, null);
    }

    public void setWebhook(SetWebhook setWebhook) {
        send(setWebhook, null);
    }

    public void deleteWebhook() {
        send(new DeleteWebhook(), null);
    }

    public void getWebhookInfo(Handler<AsyncResult<WebhookInfo>> handler) {
        send(new GetWebhookInfo(), handler);
    }
}
