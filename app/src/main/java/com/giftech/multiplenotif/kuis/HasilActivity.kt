package com.giftech.multiplenotif.kuis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftech.multiplenotif.MainActivity
import com.giftech.multiplenotif.R
import kotlinx.android.synthetic.main.activity_hasil.*

class HasilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)

        val score = intent.getIntExtra("nilai", 0)
        val a = score*20
        txtNilai.setText(a.toString())

        if (score>3) {
            txtPesan.setText("Pertahankan\n" +
                    "dan \n" +
                    "terus belajar"
            )
        } else {
            txtPesan.setText("Tetap semangat\n" +
                    "dan \n" +
                    "terus belajar"
            )

        }

        btnBelajarLagi.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}