package org.sopt.sample.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RequestLoginDTO(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String
)
