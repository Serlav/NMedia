package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemory

private val empty = Post(
    0L,
    "",
    "",
    "",
    false
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()
    val data = repository.get()
    val edited = MutableLiveData(empty)

    fun likeById(id: Long) = repository.likeById(id)

    fun edit(post: Post){
        edited.value = post
    }

    fun shareById(id: Long) = repository.shareById(id)

    fun get(): LiveData<List<Post>> = repository.get()
    fun removeById(id: Long) = repository.removeById(id)
    fun editContent(text: String) {
        val formatted = text.trim()
        if (edited.value?.content == formatted) {
            return
        }

        edited.value = edited.value?.copy(content = formatted)
    }

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
}