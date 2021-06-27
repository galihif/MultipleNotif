package com.giftech.multiplenotif.kuis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.giftech.multiplenotif.R
import kotlinx.android.synthetic.main.activity_ulasan.*

class UlasanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulasan)

        var answerList = intent.getIntegerArrayListExtra("answer")

        if(answerList!![0] != 10){
            q1.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bgf)
        }else{
            q1.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bguf)
        }
        if(answerList!![1] != 10){
            q2.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bgf)
        }else{
            q2.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bguf)
        }
        if(answerList!![2] != 10){
            q3.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bgf)
        }else{
            q3.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bguf)
        }
        if(answerList!![3] != 10){
            q4.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bgf)
        }else{
            q4.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bguf)
        }
        if(answerList!![4] != 10){
            q5.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bgf)
        }else{
            q5.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_bguf)
        }

        q1.setOnClickListener {
            var intent = Intent(applicationContext, Kuis2Activity::class.java)
            intent.putIntegerArrayListExtra("answer", answerList)
            intent.putExtra("question", 0)
            startActivity(intent)
        }
        q2.setOnClickListener {
            var intent = Intent(applicationContext, Kuis2Activity::class.java)
            intent.putIntegerArrayListExtra("answer", answerList)
            intent.putExtra("question", 1)
            startActivity(intent)
        }
        q3.setOnClickListener {
            var intent = Intent(applicationContext, Kuis2Activity::class.java)
            intent.putIntegerArrayListExtra("answer", answerList)
            intent.putExtra("question", 2)
            startActivity(intent)
        }
        q4.setOnClickListener {
            var intent = Intent(applicationContext, Kuis2Activity::class.java)
            intent.putIntegerArrayListExtra("answer", answerList)
            intent.putExtra("question", 3)
            startActivity(intent)
        }
        q5.setOnClickListener {
            var intent = Intent(applicationContext, Kuis2Activity::class.java)
            intent.putIntegerArrayListExtra("answer", answerList)
            intent.putExtra("question", 4)
            startActivity(intent)
        }

        btn_kirim.setOnClickListener {
            var nilai = answerList.count { it == 1 }
            var intent = Intent(applicationContext, HasilActivity::class.java)
            intent.putExtra("nilai", nilai)
            startActivity(intent)
            finishAffinity()
        }
    }
}