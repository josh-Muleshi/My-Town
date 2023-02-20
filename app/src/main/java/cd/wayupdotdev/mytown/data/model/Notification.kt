package cd.wayupdotdev.mytown.data.model

import java.sql.Date

data class Notification(
    val uid: String,
    val title: String,
    val adminUid: String,
    val description: String,
    val imageUrl: String,
    val date: String,
    val category: String,
    val createdAt: Date,
)