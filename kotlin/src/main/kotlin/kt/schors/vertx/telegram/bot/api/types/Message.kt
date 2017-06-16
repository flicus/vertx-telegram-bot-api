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

package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kt.schors.vertx.telegram.bot.api.types.payments.Invoice
import kt.schors.vertx.telegram.bot.api.types.payments.SuccessfulPayment

data class Message @JsonCreator constructor(
        @JsonProperty("message_id") var messageId: Int? = null,
        @JsonProperty("from") var from: User? = null,
        @JsonProperty("date") var date: Int? = null,
        @JsonProperty("chat") var chat: Chat? = null,
        @JsonProperty("forward_from") var forwardFrom: User? = null,
        @JsonProperty("forward_from_chat") var forwardFromChat: Chat? = null,
        @JsonProperty("forward_from_message_id") var forwardFromMessageId: Int? = null,
        @JsonProperty("forward_date") var forwardDate: Int? = null,
        @JsonProperty("reply_to_message") var replyToMessage: Message? = null,
        @JsonProperty("edit_date") var editDate: Int? = null,
        @JsonProperty("text") var text: String? = null,
        @JsonProperty("entities") var entities: Array<MessageEntity>? = null,
        @JsonProperty("audio") var audio: Audio? = null,
        @JsonProperty("document") var document: Document? = null,
        @JsonProperty("game") var game: Game? = null,
        @JsonProperty("photo") var photo: Array<PhotoSize>? = null,
        @JsonProperty("sticker") var sticker: Sticker? = null,
        @JsonProperty("video") var video: Video? = null,
        @JsonProperty("video_note") var videoNote: VideoNote? = null,
        @JsonProperty("voice") var voice: Voice? = null,
        @JsonProperty("caption") var caption: String? = null,
        @JsonProperty("contact") var contact: Contact? = null,
        @JsonProperty("location") var location: Location? = null,
        @JsonProperty("venue") var venue: Venue? = null,
        @JsonProperty("new_chat_members") var newChatMembers: Array<User>? = null,
        @JsonProperty("left_chat_member") var leftChatMember: User? = null,
        @JsonProperty("new_chat_title") var newChatTitle: String? = null,
        @JsonProperty("new_chat_photo") var newChatPhoto: Array<PhotoSize>? = null,
        @JsonProperty("delete_chat_photo") var deleteChatPhoto: Boolean? = null,
        @JsonProperty("group_chat_created") var groupChatCreated: Boolean? = null,
        @JsonProperty("supergroup_chat_created") var supergroupChatCreated: Boolean? = null,
        @JsonProperty("channel_chat_created") var channelChatCreated: Boolean? = null,
        @JsonProperty("migrate_to_chat_id") var migrateToChatId: Int? = null,
        @JsonProperty("migrate_from_chat_id") var migrateFromChatId: Int? = null,
        @JsonProperty("pinned_message") var pinnedMessage: Message? = null,
        @JsonProperty("invoice") var invoice: Invoice? = null,
        @JsonProperty("successful_payment") var successfulPayment: SuccessfulPayment? = null
)