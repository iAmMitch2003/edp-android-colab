package com.liceo.account.data.network.dto

import com.liceo.account.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        id = id.orEmpty(),
        fullName = fullname.orEmpty(),
        email = email.orEmpty(),
        birthdate = birthdate.orEmpty()
    )
}
