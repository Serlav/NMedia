package ru.netology.nmedia.adapter

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostAdapter(
    private val onLikeClicked: (Post) -> Unit,
    private val onShareClicked: (Post) -> Unit,
    private val onRemoveClicked: (Post) -> Unit
) : ListAdapter<Post, PostViewHolder>(PostDiffItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeClicked, onShareClicked, onRemoveClicked)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeClicked: (Post) -> Unit,
    private val onShareClicked: (Post) -> Unit,
    private val onRemoveClicked: (Post) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published

            numbersOfLikes.text = formatNumbers(post.likes)

            like.setImageResource(
                if (post.likedByMe) {
                    R.drawable.ic_liked
                } else {
                    R.drawable.ic_like
                }
            )

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onRemoveClicked(post)
                                true
                            }
                            else -> false
                        }
                    }
                    show()
                }
            }

            like.setOnClickListener {
                onLikeClicked(post)
            }
            numbersOfShared.text = formatNumbers(post.share)

            share.setImageResource(
                if (post.share > 0) {
                    R.drawable.ic_shared_24
                } else {
                    R.drawable.ic_share
                }
            )

            share.setOnClickListener {
                onShareClicked(post)
            }
        }
    }
}

class PostDiffItemCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem == newItem
}


private fun formatNumbers(num: Int): String {
    val dec = DecimalFormat("#.#")

    return when {
        num <= 999 -> {
            num.toString()
        }
        num < 999_999 -> {
            dec.format(num / 1000f).toString() + "k"
        }
        else -> {
            dec.format(num / 1_000_000f).toString() + "M"
        }
    }
}