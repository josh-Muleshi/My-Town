package cd.wayupdotdev.mytown.data.model

import java.util.Date

data class User(
    val uid: String = "",
    val name: String = "",
    val tel: String = "",
    val createdAt: Date? = null
)
