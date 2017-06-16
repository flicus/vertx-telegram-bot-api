package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class User
@JsonCreator constructor(
        @JsonProperty("id")
        var id: Int? = null,
        @JsonProperty("first_name")
        var firstName: String? = null,
        @JsonProperty("last_name")
        var lastName: String? = null,
        @JsonProperty("username")
        var username: String? = null,
        @JsonProperty("language_code")
        var languageCode: String? = null
)