package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36"
        )

        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published

            initLikes(post)
            setShare(post)

            like.setOnClickListener {
                setLike(post)
            }
            numbersOfLikes.setOnClickListener{
                setLike(post)
            }

            share.setOnClickListener {
                post.sharedByMe = !post.sharedByMe
                setShare(post)
            }
        }

    }

    private fun ActivityMainBinding.setLikeImage(post: Post) {
        like.setImageResource(
            if (post.likedByMe) {
                R.drawable.ic_liked
            } else {
                R.drawable.ic_like
            }
        )
    }

    private fun ActivityMainBinding.initLikes(post: Post) {
        numbersOfLikes.text = formatNumbers(post.likes)
    }


    private fun ActivityMainBinding.setLike(post: Post) {
        post.likedByMe = !post.likedByMe
        setLikeImage(post)
        if (post.likedByMe) {
            post.likes++
        } else {
            post.likes--
        }
        numbersOfLikes.text = formatNumbers(post.likes)
    }

    private fun formatNumbers(num: Int): String {
        return if (num < 999) {
            num.toString()
        } else if (num < 999_999) {
            (num / 1000).toString() + "k"
        } else {
            (num / 1000000).toString() + "M"
        }
    }


    private fun ActivityMainBinding.setShare(post: Post) {
        share.setImageResource(
            if (post.sharedByMe) {
                R.drawable.ic_shared_24
            } else {
                R.drawable.ic_share
            }
        )
    }
}