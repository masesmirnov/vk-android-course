package ru.masesmirnov.vkandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<TextView>(R.id.received_text).text =
            intent.getStringExtra(EXTRA_TEXT)?.takeIf(String::isNotBlank) ?: getString(R.string.no_text_received)
    }

    companion object {
        private const val EXTRA_TEXT = "ru.masesmirnov.vkandroid.extra.TEXT"

        fun newIntent(context: Context, text: String): Intent =
            Intent(context, SecondActivity::class.java).putExtra(EXTRA_TEXT, text)
    }
}