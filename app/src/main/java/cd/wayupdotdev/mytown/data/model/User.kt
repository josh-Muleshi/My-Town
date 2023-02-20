package cd.wayupdotdev.mytown.data.model

import java.util.Date

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String="",
    val profileUrl: String="",
    val isValid: Boolean = true,
    val createdAt: Date? = null
)
