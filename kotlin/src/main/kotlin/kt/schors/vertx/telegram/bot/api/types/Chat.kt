package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Chat
@JsonCreator constructor(
        @JsonProperty("id")
        var id: Int? = null,
        @JsonProperty("type")
        var type: String? = null,
        @JsonProperty("title")
        var title: String? = null,
        @JsonProperty("username")
        var username: String? = null,
        @JsonProperty("first_name")
        var firstName: String? = null,
        @JsonProperty("last_name")
        var lastName: String? = null,
        @JsonProperty("all_members_are_administrators")
        var allAdmins: Boolean? = null
)