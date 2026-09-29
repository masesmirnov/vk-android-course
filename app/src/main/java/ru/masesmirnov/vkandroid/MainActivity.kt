package ru.masesmirnov.vkandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        input = findViewById(R.id.input)
        findViewById<Button>(R.id.open_second_button).setOnClickListener { openSecondActivity() }
    }

    private fun openSecondActivity() {
        val text = validInput(R.string.error_empty_text, String::isNotEmpty) ?: return
        startActivity(SecondActivity.newIntent(this, text))
    }

    private fun validInput(@StringRes errorRes: Int, isValid: (String) -> Boolean): String? {
        val value = input.text.toString().trim()
        if (isValid(value)) return value
        input.error = getString(errorRes)
        return null
    }
}