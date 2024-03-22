package com.example.users.data.dto

import com.example.users.domain.entity.ResultsDtoEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class ResultsDto(
    @Json(name = "items")
    override val items: List<UserDto>
): ResultsDtoEntity
