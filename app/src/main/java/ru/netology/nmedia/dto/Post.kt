package ru.netology.nmedia.dto

data class Post(
    val id: Int = 0,
    val author: String = "",
    val content: String = "",
    val published: String = "",
    var likedByMe: Boolean = false,
    val likes: Int = 0,
    val share: Int = 1000
)