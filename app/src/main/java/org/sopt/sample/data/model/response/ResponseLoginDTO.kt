package org.sopt.sample.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDTO(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: User?
) {
    @Serializable
    data class User(
        val id: Int,
        val name: String,
        val profileImage: String?,
        val bio: String?,
        val email: String,
        val password: String
    )
}


