package com.giftech.multiplenotif.kuis

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.giftech.multiplenotif.R
import kotlinx.android.synthetic.main.activity_kuis.*

class Kuis2Activity : AppCompatActivity() {
    private val mQuestionLibrary: Soal = Soal()

    private lateinit var mQuestionView: TextView
    private lateinit var mQuestionTitle: TextView
    private lateinit var mButtonChoice1: Button
    private lateinit var mButtonChoice2: Button
    private lateinit var mButtonChoice3: Button
    private lateinit var mButtonNext: Button

    private lateinit var choice1:String
    private lateinit var choice2:String
    private lateinit var choice3:String

    private lateinit var mAnswer:String
    private var mUserAnswer = ""
    private var mScore = 0
    private var mQuestionNumber = 0

    private var answerList = arrayListOf<Int>(10,10,10,10,10)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuis2)

        mQuestionView = findViewById<View>(R.id.question) as TextView
        mQuestionTitle = findViewById<View>(R.id.question_title) as TextView
        mButtonChoice1 = findViewById<View>(R.id.choice1) as Button
        mButtonChoice2 = findViewById<View>(R.id.choice2) as Button
        mButtonChoice3 = findViewById<View>(R.id.choice3) as Button
        mButtonNext = findViewById<View>(R.id.btn_next) as Button

        answerList = intent.getIntegerArrayListExtra("answer")!!
        mQuestionNumber = intent.getIntExtra("question", 0)


        updateQuestion()

        mButtonChoice1.setOnClickListener {
            mUserAnswer = choice1
            mButtonChoice1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.brown))

            mButtonChoice2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
            mButtonChoice3.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
        }

        mButtonChoice2.setOnClickListener {
            mUserAnswer = choice2
            mButtonChoice2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.brown))

            mButtonChoice1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
            mButtonChoice3.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
        }

        mButtonChoice3.setOnClickListener {
            mUserAnswer = choice3
            mButtonChoice3.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.brown))

            mButtonChoice1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
            mButtonChoice2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
        }

        btn_next.setOnClickListener {
            if(mUserAnswer === mAnswer){
                mScore += 1
                answerList[mQuestionNumber-1] = 1
            } else{
                answerList[mQuestionNumber-1] = 0
            }
            val intent = Intent(applicationContext, UlasanActivity::class.java)
            intent.putIntegerArrayListExtra("answer", answerList)
            startActivity(intent)
        }
    }

    private fun updateQuestion() {
        mUserAnswer = ""
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber))
        mQuestionTitle.setText("Pertanyaan ${mQuestionNumber+1}")
        mButtonChoice1.setText("A. " + mQuestionLibrary.getChoice1(mQuestionNumber))
        mButtonChoice2.setText("B. " + mQuestionLibrary.getChoice2(mQuestionNumber))
        mButtonChoice3.setText("C. " + mQuestionLibrary.getChoice3(mQuestionNumber))

        choice1 = mQuestionLibrary.getChoice1(mQuestionNumber)
        choice2 = mQuestionLibrary.getChoice2(mQuestionNumber)
        choice3 = mQuestionLibrary.getChoice3(mQuestionNumber)

        mButtonChoice1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
        mButtonChoice2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
        mButtonChoice3.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))

        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber)

        if (mQuestionNumber < Soal.mQuestions.size) {
            mQuestionNumber++
        } else {
            Toast.makeText(this, "Kuis selesai", Toast.LENGTH_SHORT).show()
        }
    }
}