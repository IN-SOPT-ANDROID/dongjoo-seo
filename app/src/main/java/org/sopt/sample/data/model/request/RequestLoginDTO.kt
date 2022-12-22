package org.sopt.sample.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDTO(
    val id: String,
    val password: String
)
