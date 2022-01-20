package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter {
            viewModel.likeById(it.id)
        }
        binding.container.adapter = adapter

        viewModel.data.observe(this, adapter::submitList)
    }
}


//                initLikes(post)
//                initShare(post)
//
//
//                numbersOfLikes.setOnClickListener {
//                    viewModel.like()
//                }
//
//                share.setOnClickListener {
//                    viewModel.share()
//                }
//                numbersOfShared.setOnClickListener {
//                    viewModel.share()
//                }
//            }
//        }
//
//
//        private fun ActivityMainBinding.initLikes(post: Post) {
//            numbersOfLikes.text = formatNumbers(post.likes)
//        }
//
//        private fun ActivityMainBinding.initShare(post: Post) {
//            numbersOfShared.text = formatNumbers(post.share)
//            setShareImage(post)
//        }
//
//        private fun ActivityMainBinding.setShareImage(post: Post) {
//            share.setImageResource(
//                if (post.share > 0) {
//                    R.drawable.ic_shared_24
//                } else {
//                    R.drawable.ic_share
//                }
//            )
//        }
