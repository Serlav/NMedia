package ru.netology.nmedia.dto

data class Post(
    val id: Int = 0,
    val author: String = "",
    val content: String = "",
    val published: String = "",
    var likedByMe: Boolean = false,
    var sharedByMe: Boolean = false,
    var likes: Int = 100
)
