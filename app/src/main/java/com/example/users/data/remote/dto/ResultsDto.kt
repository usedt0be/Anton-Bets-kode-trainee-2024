package com.example.users.data.remote.dto

import com.example.users.domain.entity.ResultsEntity
import com.example.users.domain.entity.UsersEntity

data class ResultsDto(
    override val users: List<UsersEntity>
):ResultsEntity
