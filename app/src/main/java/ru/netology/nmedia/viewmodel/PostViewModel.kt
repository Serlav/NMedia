package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemory

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()
    val data = repository.get()
    fun likeById(id: Long) = repository.likeById(id)


    fun share() {
        repository.share()
    }

    fun get(): LiveData<List<Post>> = repository.get()
}