package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {

    private var posts = List(10) {
        Post(
            id = it.toLong(),
            author = "Нетология. Университет интернет-профессий будущего",
            content = "№ $it Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            share = 0
        )
    }
    private val data: MutableLiveData<List<Post>> = MutableLiveData(posts)

    override fun likeById(id: Long) {
        data.value = data.value?.map {
            if (id == it.id) {
                it.copy(
                    likedByMe = !it.likedByMe,
                    likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
                )
            } else {
                it
            }
        }
    }

    override fun shareById(id: Long) {
        data.value = data.value?.map {
            if (id == it.id) {
                it.copy(share = it.share + 1)
            } else {
                it
            }
        }
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(post.copy(id = posts.firstOrNull()?.id?.inc() ?: 0)) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (it.id == post.id){
                it.copy(content = post.content)
            } else{
                it
            }
        }
        data.value = posts
    }

    override fun get(): LiveData<List<Post>> = data

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }
}