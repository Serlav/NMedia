package ru.netology.nmedia

import android.icu.text.DecimalFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepositoryInMemory
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        with(binding) {
            viewModel.get().observe(this@MainActivity) { post ->

                author.text = post.author
                content.text = post.content
                published.text = post.published

                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like
                )

                initLikes(post)
                initShare(post)

                like.setOnClickListener {
                    viewModel.like()
                }
                numbersOfLikes.setOnClickListener {
                    viewModel.like()
                }

                share.setOnClickListener {
                    viewModel.share()
                }
                numbersOfShared.setOnClickListener {
                    viewModel.share()
                }
            }
        }
    }

    private fun ActivityMainBinding.initLikes(post: Post) {
        numbersOfLikes.text = formatNumbers(post.likes)
    }

    private fun ActivityMainBinding.initShare(post: Post) {
        numbersOfShared.text = formatNumbers(post.share)
        setShareImage(post)
    }

    private fun ActivityMainBinding.setShareImage(post: Post) {
        share.setImageResource(
            if (post.share > 0) {
                R.drawable.ic_shared_24
            } else {
                R.drawable.ic_share
            }
        )
    }

    private fun formatNumbers(num: Int): String {
        val dec = DecimalFormat("#.#")

        return if (num <= 999) {
            num.toString()
        } else if (num < 999_999) {
            dec.format(num / 1000f).toString() + "k"
        } else {
            dec.format(num / 1_000_000f).toString() + "M"
        }
    }
}