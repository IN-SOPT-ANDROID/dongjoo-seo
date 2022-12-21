package org.sopt.sample.data.model.request

import kotlinx.serialization.SerialName

data class RequestSignUpDTO(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String
)
