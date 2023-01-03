package com.example.fourpicsoneword

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.fourpicsoneword.data.Constants
import com.example.fourpicsoneword.data.Question
import com.example.fourpicsoneword.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private val questions = Constants.getQuestion()
    private var currentQuestionId = -1
    var clickedImageId = -1
    private val optionLetters = mutableListOf<TextView>()
    private val answerLetters = mutableListOf<TextView>()

    /**
     * Paydalaniwshi terip atirgan haripler
     */
    private val currentAnswers = mutableListOf<Pair<String, TextView>>()

    /**
     *  VARiantta berilgen 12 harip
     */
    private val currentOptions = mutableListOf<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            optionLetters.addAll(
                    listOf(
                        wordOption1, wordOption2, wordOption3, wordOption4, wordOption5,wordOption6,
                        wordOption7, wordOption8, wordOption9, wordOption10, wordOption11,wordOption12,
                    )
            )

            answerLetters.addAll(
                listOf(
                    wordAnswer1, wordAnswer2, wordAnswer3, wordAnswer4,
                    wordAnswer5, wordAnswer6, wordAnswer7, wordAnswer8,
                )
            )
        }

        optionLetters.forEach { optionTV ->
            optionTV.addTextChangedListener {
                val letter = it.toString()
                optionTV.isEnabled = letter.isNotEmpty()
            }
        }

        answerLetters.forEach { answerTV ->
            answerTV.addTextChangedListener {
                val letter = it.toString()
                answerTV.isEnabled = letter.isNotEmpty()
            }
        }

        currentQuestionId++
        setQuestion()


        binding.apply {
            wordOption1.setOnClickListener { optionClick(it as TextView) }
            wordOption2.setOnClickListener { optionClick(it as TextView) }
            wordOption3.setOnClickListener { optionClick(it as TextView) }
            wordOption4.setOnClickListener { optionClick(it as TextView) }
            wordOption5.setOnClickListener { optionClick(it as TextView) }
            wordOption6.setOnClickListener { optionClick(it as TextView) }
            wordOption7.setOnClickListener { optionClick(it as TextView) }
            wordOption8.setOnClickListener { optionClick(it as TextView) }
            wordOption9.setOnClickListener { optionClick(it as TextView) }
            wordOption10.setOnClickListener { optionClick(it as TextView) }
            wordOption11.setOnClickListener { optionClick(it as TextView) }
            wordOption12.setOnClickListener { optionClick(it as TextView) }

            wordAnswer1.setOnClickListener { answerClick(it as TextView) }
            wordAnswer2.setOnClickListener { answerClick(it as TextView) }
            wordAnswer3.setOnClickListener { answerClick(it as TextView) }
            wordAnswer4.setOnClickListener { answerClick(it as TextView) }
            wordAnswer5.setOnClickListener { answerClick(it as TextView) }
            wordAnswer6.setOnClickListener { answerClick(it as TextView) }
            wordAnswer7.setOnClickListener { answerClick(it as TextView) }
            wordAnswer8.setOnClickListener { answerClick(it as TextView) }

            playBtn.setOnClickListener {
                currentQuestionId++
                setQuestion()
            }
            binding.btnBack.setOnClickListener {
                finish()
            }

            ivShine.startAnimation(
                AnimationUtils.loadAnimation(
                    this@GameActivity, R.anim.rotate_anim
                )
            )

            image1.setOnClickListener {
                clickedImageId = 0
                bigImage.setImageResource(questions[currentQuestionId].images[0])
                bigImage.visibility = View.VISIBLE
                bigImage.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@GameActivity, R.anim.animation_up_one
                    )
                )
            }

            image2.setOnClickListener {
                clickedImageId = 1
                bigImage.setImageResource(questions[currentQuestionId].images[1])
                bigImage.visibility = View.VISIBLE
                bigImage.startAnimation(AnimationUtils.loadAnimation(
                    this@GameActivity, R.anim.animation_up_two
                ))
            }

            image3.setOnClickListener {
                clickedImageId = 2
                bigImage.setImageResource(questions[currentQuestionId].images[2])
                bigImage.visibility = View.VISIBLE
                bigImage.startAnimation(AnimationUtils.loadAnimation(
                    this@GameActivity, R.anim.animation_up_three
                ))
            }

            image4.setOnClickListener {
                clickedImageId = 3
                bigImage.setImageResource(questions[currentQuestionId].images[3])
                bigImage.visibility = View.VISIBLE

                bigImage.startAnimation(AnimationUtils.loadAnimation(
                    this@GameActivity, R.anim.animation_up_four
                ))
            }

            bigImage.setOnClickListener {
                when (clickedImageId){
                    0 -> {
                        bigImage.startAnimation(AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.animation_down_one
                        ))
                        Handler().postDelayed({
                            bigImage.visibility = View.GONE
                        }, 200L)
                    }
                    1 -> {
                        bigImage.startAnimation(AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.animation_down_two
                        ))
                        Handler().postDelayed({
                            bigImage.visibility = View.GONE
                        }, 200L)
                    }
                    2 -> {
                        bigImage.startAnimation(AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.animation_down_three
                        ))
                        Handler().postDelayed({
                            bigImage.visibility = View.GONE
                        }, 200L)
                    }
                    3 -> {
                        bigImage.startAnimation(AnimationUtils.loadAnimation(
                            this@GameActivity, R.anim.animation_down_four
                        ))
                        Handler().postDelayed({
                            bigImage.visibility = View.GONE
                        }, 200L)
                    }
                }
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val currentQuestion = questions[currentQuestionId]

        binding.apply {
            currentAnswers.clear()
            updateAnswer(currentQuestion)
            showContinue(false)

            tvLevel.text = (currentQuestionId + 1).toString()

            image1.setImageResource(currentQuestion.images[0])
            image2.setImageResource(currentQuestion.images[1])
            image3.setImageResource(currentQuestion.images[2])
            image4.setImageResource(currentQuestion.images[3])

            val options = currentQuestion.answer.toMutableList()

            repeat (12 - options.size) {
                options.add(Random.nextInt(1040, 1072).toChar())
            }
            options.shuffle()
            currentOptions.clear()
            currentOptions.addAll(options)

            options.forEachIndexed { index, letter ->
                optionLetters[index].text = letter.toString()
            }

            answerLetters.forEach {
                it.isVisible = true
                it.isClickable = true
            }

            for (i in currentQuestion.answer.length until  answerLetters.size) {
                answerLetters[i].isVisible = false
            }
        }

    }

    private fun showContinue(show: Boolean) {
        binding.apply {
            bgOverlay.isVisible = show
            ivShine.isVisible = show
            playBtn.isVisible = show
            tvNext.isVisible = show

        ivShine.startAnimation(
            AnimationUtils.loadAnimation(
                this@GameActivity, R.anim.rotate_anim
            )
        )
            if(!show) {
                ivShine.clearAnimation()
            }

        answerLetters.forEach {
            it.isClickable = false
            }
        }
    }

    private fun optionClick(optionTV: TextView) {
        val currentQuestion = questions[currentQuestionId]

        val index = optionLetters.indexOf(optionTV)
        val letter = currentOptions[index]

        val pairIndex = currentAnswers.indexOfFirst { it.first == "" }
        if (pairIndex == -1) {
            currentAnswers.add(Pair(letter.toString(), optionTV))
        } else {
            currentAnswers[pairIndex] = Pair(letter.toString(), optionTV)
        }

        updateAnswer(currentQuestion)
        optionTV.text = ""

    }

    private fun answerClick(answerTV: TextView) {
        answerTV.text = ""
        val index = answerLetters.indexOf(answerTV)
        val pair = currentAnswers[index]

        pair.second.text = pair.first
        currentAnswers[index] = Pair("", pair.second)
        updateAnswer(questions[currentQuestionId])
    }

    private fun updateAnswer(question: Question) {
        if (currentAnswers.isEmpty()) {
            answerLetters.forEach {
                it.text = ""
            }
            optionLetters.forEach { option ->
                option.isClickable = true
            }
            return
        }

        currentAnswers.forEachIndexed { index, pair ->
            answerLetters[index].text = pair.first
        }

        if (question.answer.length == currentAnswers.filter { it.first.isNotEmpty() }.size) {
            if (question.answer == currentAnswers.map { it.first }.joinToString("")) {
                showContinue(true)
            }
            optionLetters.forEach { option ->
                option.isClickable = false
            }
        } else {
            optionLetters.forEach { option ->
                option.isClickable = true
            }
        }
    }
}

