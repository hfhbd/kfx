package io.github.hfhbd.kfx

import kotlinx.serialization.Serializable

@Serializable
enum class StatusCode(val value: Int) {
    OK(200),
    Created(201),
    Accepted(202),
    NoContent(204),
    BadRequest(400),
    Unauthorized(401),
    Forbidden(403),
    NotFound(404),
    NotAcceptable(406),
    Conflict(409),
    LengthRequired(411),
    ContentTooLarge(413),
    TooManyRequests(439),
    InternalServerError(500),
    ;

    companion object {
        fun fromValue(value: Int) = entries.first { it.value == value }
    }
}
