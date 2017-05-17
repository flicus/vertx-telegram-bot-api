/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2017  schors
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
import org.schors.vertx.telegram.bot.commands.CommandHandler;
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
//    private CommandHandler commandHandler;

    public TelegramBot(Vertx vertx, TelegramOptions options) {
        this.vertx = vertx;
        this.botOptions = options;

        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        HttpClientOptions httpOptions = new HttpClientOptions()
//                .setKeepAlive(true)
                .setSsl(true)
//                .setOpenSslEngineOptions(new OpenSSLEngineOptions())
//                .setUseAlpn(true)
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

    public static TelegramBot create(Vertx vertx, TelegramOptions options, CommandHandler cm) {
        TelegramBot tb = new TelegramBot(vertx, options);
        cm.setBot(tb);
        return tb;
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

//    public CommandHandler getCommandHandler() {
//        return commandHandler;
//    }

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

    public void sendDocument(SendDocument document) {
        sendDocument(document, null);
    }

    public void sendDocument(SendDocument document, Handler<AsyncResult<Message>> handler) {
        vertx.executeBlocking(future -> {
            HttpClientRequest request = createRequest(document, future);
            java.io.File file = new java.io.File(document.getDocument());
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", document.getChatId())
                    .putTextBody("reply_to_message_id", document.getReplyToMessageId())
                    .putTextBody("caption", document.getCaption())
                    .putTextBody("disable_notification", document.isDisableNotification())
                    .putTextBody("reply_markup", document.getReplyMarkup())
                    .putBinaryBody("document", document.getDocument(), "application/octet-stream", file.getName(), event -> {
                        multipartHelper.stop();
                        request.end();
                    });
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
            java.io.File file = new java.io.File(photo.getPhoto());
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", photo.getChatId())
                    .putTextBody("reply_to_message_id", photo.getReplyToMessageId())
                    .putTextBody("caption", photo.getCaption())
                    .putTextBody("disable_notification", photo.isDisableNotification())
                    .putTextBody("reply_markup", photo.getReplyMarkup())
                    .putBinaryBody("photo", photo.getPhoto(), "application/octet-stream", file.getName(), event -> {
                        multipartHelper.stop();
                        request.end();
                    });
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
            java.io.File file = new java.io.File(audio.getAudio());
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", audio.getChatId())
                    .putTextBody("reply_to_message_id", audio.getReplyToMessageId())
                    .putTextBody("caption", audio.getCaption())
                    .putTextBody("disable_notification", audio.isDisableNotification())
                    .putTextBody("reply_markup", audio.getReplyMarkup())
                    .putTextBody("duration", audio.getDuration())
                    .putTextBody("performer", audio.getPerformer())
                    .putTextBody("title", audio.getTitle())
                    .putBinaryBody("audio", audio.getAudio(), "application/octet-stream", file.getName(), event -> {
                        multipartHelper.stop();
                        request.end();
                    });
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
            java.io.File file = new java.io.File(sticker.getSticker());
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", sticker.getChatId())
                    .putTextBody("reply_to_message_id", sticker.getReplyToMessageId())
                    .putTextBody("disable_notification", sticker.isDisableNotification())
                    .putTextBody("reply_markup", sticker.getReplyMarkup())
                    .putBinaryBody("sticker", sticker.getSticker(), "application/octet-stream", file.getName(), event -> {
                        multipartHelper.stop();
                        request.end();
                    });
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
            java.io.File file = new java.io.File(video.getVideo());
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", video.getChatId())
                    .putTextBody("reply_to_message_id", video.getReplyToMessageId())
                    .putTextBody("disable_notification", video.isDisableNotification())
                    .putTextBody("reply_markup", video.getReplyMarkup())
                    .putTextBody("duration", video.getDuration())
                    .putTextBody("caption", video.getCaption())
                    .putTextBody("width", video.getHeight())
                    .putTextBody("height", video.getWidth())
                    .putBinaryBody("video", video.getVideo(), "application/octet-stream", file.getName(), event -> {
                        multipartHelper.stop();
                        request.end();
                    });
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
            java.io.File file = new java.io.File(voice.getVoice());
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .putTextBody("chat_id", voice.getChatId())
                    .putTextBody("reply_to_message_id", voice.getReplyToMessageId())
                    .putTextBody("caption", voice.getCaption())
                    .putTextBody("disable_notification", voice.isDisableNotification())
                    .putTextBody("reply_markup", voice.getReplyMarkup())
                    .putTextBody("duration", voice.getDuration())
                    .putBinaryBody("voice", voice.getVoice(), "application/octet-stream", file.getName(), event -> {
                        multipartHelper.stop();
                        request.end();
                    });
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

    public void setWebhook(SetWebhook setWebhook) {
        send(setWebhook, null);
    }

}
