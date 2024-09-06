package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val skip = intent.getIntExtra("skip", 0)
        val correct = intent.getIntExtra("correct" ,0)
        val wrong = intent.getIntExtra("wrong", 0)


        binding.skipAnswerCount.text = "Skipped Answers: $skip"
        binding.correctAnswerCount.text = "Correct Answers: $correct"
        binding.wrongAnswerCount.text = "Wrong Answers: $wrong"


        binding.finishButton.setOnClickListener {
            val intent = Intent(this , MainActivity :: class.java)
            startActivity(intent)
            finish()

        }

    }
}