package ru.netology.nmedia.dto

data class Post(
    val id: Int = 0,
    val author: String = "",
    val content: String = "",
    val published: String = "",
    var likedByMe: Boolean = false
)