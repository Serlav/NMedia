package ru.netology.nmedia.dto

data class Post(
    val id: Long = 0,
    val author: String = "",
    val content: String = "",
    val published: String = "",
    var likedByMe: Boolean = false,
    var likes: Int = 0,
    val share: Int = 10
)