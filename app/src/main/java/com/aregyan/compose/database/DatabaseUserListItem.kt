package com.aregyan.compose.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aregyan.compose.domain.UserListItem

@Entity
data class DatabaseUserListItem constructor(
    @PrimaryKey
    val id: Int,
    val avatar: String,
    val username: String
)

fun List<DatabaseUserListItem>.asDomainModel(): List<UserListItem> {
    return map {
        UserListItem(
            id = it.id,
            avatar = it.avatar,
            username = it.username
        )
    }
}