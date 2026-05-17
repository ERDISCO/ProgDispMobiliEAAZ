package com.example.progdispmobilieaaz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import com.example.progdispmobilieaaz.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val bmi = findViewById<Button>(R.id.BMI)
        val bsa = findViewById<Button>(R.id.BSA)
        val easi = findViewById<Button>(R.id.EASI)
        val pasi = findViewById<Button>(R.id.PASI)
        bsa.setOnClickListener {
            val intent = Intent(this, BSA::class.java)
            startActivity(intent)
        }
        easi.setOnClickListener {
            val intent = Intent(this, EASI::class.java)
            startActivity(intent)
        }
        bmi.setOnClickListener {
            val intent = Intent(this, BMI::class.java)
            startActivity(intent)
        }

        }
}