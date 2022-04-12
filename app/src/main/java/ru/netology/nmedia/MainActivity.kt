package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostActionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(
            object : PostActionListener {
                override fun edit(post: Post) {
                    viewModel.edit(post)
                }

                override fun like(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun share(post: Post) {
                    viewModel.shareById(post.id)
                }

                override fun remove(post: Post) {
                    viewModel.removeById(post.id)
                }
            })

        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        with(binding) {

            cancel.setOnClickListener {
                content.setText("")
                content.clearFocus()
                AndroidUtils.hideKeyboard(content)
                cancel.visibility = View.GONE
                return@setOnClickListener
            }
            save.setOnClickListener {

                val text = content.text?.toString()
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.blank_content_error),
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                viewModel.editContent(text)
                viewModel.save()
                cancel.visibility = View.GONE
                content.setText("")
                content.clearFocus()
                AndroidUtils.hideKeyboard(content)
            }

            viewModel.edited.observe(this@MainActivity) {
                if (it.id == 0L) {
                    return@observe
                }
                group.visibility = View.VISIBLE
                content.requestFocus()
                content.setText(it.content)
            }
        }
    }
}