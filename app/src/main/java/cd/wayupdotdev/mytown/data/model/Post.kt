package cd.wayupdotdev.mytown.data.model

import java.util.Date

data class Post(
    val uid: String = "",
    val userUid: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val createdAt: Date? = null,
    val isFavorite: Boolean = false,
    val notification: Boolean = true
){
    fun toRoomChurch(): RoomChurch {
        return RoomChurch(
            uid = uid,
            userUid = userUid,
            description = description,
            imageUrl = imageUrl,
            createdAt = java.sql.Date(System.currentTimeMillis()),
            isFavorite = isFavorite,
        )
    }
}

//val Post.generatedId: String
//    get() = title.filterNot { it.isWhitespace() }.filterNot { it == '"' }.take(20)