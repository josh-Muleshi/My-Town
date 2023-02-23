package cd.wayupdotdev.mytown.domain.dto

import cd.wayupdotdev.mytown.data.model.User
import java.util.*

data class CustomFirebaseUser(
    val uid: String,
    val email: String,
    val name: String,
    val profileUrl: String,
    val isValid : Boolean,
    val createdAt : Date
)

fun CustomFirebaseUser.toUser(): User {
    return User(
        uid = uid,
        name = name,
        email = email,
        profileUrl = profileUrl,
        isValid = isValid,
        createdAt = createdAt
    )
}