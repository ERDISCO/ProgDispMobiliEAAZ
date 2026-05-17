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
        if(altezza != null && (altezza / 100.0) > 0.40){
            altezza = (altezza/100.0)
        }

        if (Peso != null && altezza != null) {
            val bmi = (Peso / (altezza * altezza))
            binding.tvRisultati.text = "il tuo BMI è: %.2f".format(bmi)
            binding.tvSano.text = "considerato: %s".format(sanoMessaggio(bmi))
        }
        else{
            binding.tvRisultati.text = "Dati mancanti"
        }
    }

    private fun sanoMessaggio(bmi: Double): String
    {
        if (bmi < 18.5)
            return "sottopeso"
        if (bmi < 25.0)
            return "sano"
        if (bmi < 30.0)
            return "sovrappeso"
        if(bmi < 35.0)
            return "obesita moderata (1)"
        if(bmi in 35.0..39.9)
            return "obesita grave (2)"
        if(bmi >= 40.0 )
            return "obesita estrema (3)"
        return "errore"
    }
}