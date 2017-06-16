package kt.schors.vertx.telegram.bot.api.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Contact
@JsonCreator constructor(
        @JsonProperty("phone_number")
        var phoneNumber: String? = null,
        @JsonProperty("first_name")
        var firstName: String? = null,
        @JsonProperty("last_name")
        var lastName: String? = null,
        @JsonProperty("user_id")
        var userId: Int? = null
)