package com.dewildte.lightning.network

import com.dewildte.lightning.dtos.users.UserDTO
import com.dewildte.lightning.dtos.users.UserIdDTO
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId

class UserDtoMapper {
    fun mapToUser(dto: UserDTO): User {
        return with(dto) {
            User(id = UserId(value = id.value))
        }
    }


    fun mapToDto(user: User): UserDTO {
        return with(user) {
            UserDTO(id = UserIdDTO(value = id.value))
        }
    }
}