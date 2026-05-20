package com.example.progdispmobilieaaz

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.progdispmobilieaaz.databinding.ActivityBmiBinding
class BMI : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.btnIndietroBMI.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnCalcola.setOnClickListener {
            calcoloBMI()
        }
    }
    private fun calcoloBMI()
    {
        var altezza = binding.PickerAltezza.text.toString().toDoubleOrNull()
        val Peso = binding.PickerPeso.text.toString().toDoubleOrNull()
        if (altezza == null) {
            binding.PickerAltezza.error = "Inserisci l'altezza"
        }
        if (Peso == null) {
            binding.PickerPeso.error = "Inserisci il peso"
        }
        if(altezza != null && (altezza / 100.0) > 0.40){
            altezza = (altezza/100.0)
        }

        if (Peso != null && altezza != null) {
            val bmi = (Peso / (altezza * altezza))
            binding.tvRisultati.text = "il tuo BMI è: %.1f".format(bmi)
            binding.tvSano.text = "considerato: %s".format(sanoMessaggio(bmi))
        }
        else{
            binding.tvRisultati.text = "Dati mancanti"
        }
    }

    private fun sanoMessaggio(bmi: Double): String = when {
        bmi < 18.5 -> "sottopeso"
        bmi < 25.0 -> "sano"
        bmi < 30.0 -> "sovrappeso"
        bmi < 35.0 -> "obesita moderata (1)"
        bmi in 35.0..39.9 -> "obesita grave (2)"
        bmi >= 40.0 -> "obesita estrema (3)"
        else -> "errore"
    }
}