package com.example.fourpicsoneword

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fourpicsoneword.data.Constants
import com.example.fourpicsoneword.data.Question
import com.example.fourpicsoneword.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var questions: List<Question>
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CheckLifeCycle", "onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        questions = Constants.getQuestion()

        binding.playBtn.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("CheckLifeCycle", "onStart")
        val questionId = prefs.getInt(Constants.LEVEL, 0)
        setQuestion(questionId)
    }

    override fun onResume() {
        super.onResume()
        Log.d("CheckLifeCycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("CheckLifeCycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CheckLifeCycle", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("CheckLifeCycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CheckLifeCycle", "onDestroy")
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(questionId: Int) {
        val currentQuestion = questions[questionId]

        binding.apply {
            val cycle = prefs.getInt(Constants.CYCLE, 0)
            binding.levelMain.text = (cycle * questions.size + questionId + 1).toString()

            image1.setImageResource(currentQuestion.images[0])
            image2.setImageResource(currentQuestion.images[1])
            image3.setImageResource(currentQuestion.images[2])
            image4.setImageResource(currentQuestion.images[3])
        }

    }
}