package cd.wayupdotdev.mytown.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "church")
data class RoomChurch(
    @PrimaryKey
    val uid: String,
    val userUid: String,
    val description: String,
    val imageUrl: String,
    val createdAt: Date,
    val isFavorite: Boolean
) {
    fun toPost(): Post {
        return Post(
            uid = uid,
            userUid = userUid,
            description = description,
            imageUrl = imageUrl,
            createdAt = createdAt,
            isFavorite = isFavorite
        )
    }
}
