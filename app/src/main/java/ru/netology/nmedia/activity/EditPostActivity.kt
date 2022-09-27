package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityEditPostBinding

class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener {
            val intent = Intent()
            val text = binding.contentNew.text
            if (text.isNullOrBlank()) {
                setResult(RESULT_CANCELED, intent)
            } else {
                val content = text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }
}