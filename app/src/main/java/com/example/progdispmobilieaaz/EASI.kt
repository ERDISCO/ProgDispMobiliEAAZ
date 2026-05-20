package com.example.progdispmobilieaaz

import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.progdispmobilieaaz.databinding.ActivityEasiBinding

class EASI : AppCompatActivity() {
    private lateinit var binding: ActivityEasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEasiBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val distretto = findViewById<EditText>(R.id.DistrettoEASI)
        val eritema = findViewById<EditText>(R.id.EritemaEASI)
        val edema = findViewById<EditText>(R.id.Edema)
        val escoriazione = findViewById<EditText>(R.id.Escoriazione)
        val linchenif = findViewById<EditText>(R.id.Linchenificazione)
        val areaCoin = findViewById<EditText>(R.id.AreaCoinEASI)

        binding.btnIndietroEASI.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.EASICalculator.setOnClickListener {
            var valido = true
            val distrettoTesto = distretto.text.toString()
            if (distrettoTesto.isEmpty() || !DistrettoValido(distrettoTesto.first())) {
                distretto.error = "Inserire un distretto valido: H, U, T, L"
                valido = false
            }

            val vEritema = leggiCampo(eritema, 0..3)
            val vEdema = leggiCampo(edema, 0..3)
            val vEscoriazione = leggiCampo(escoriazione, 0..3)
            val vLinchenif = leggiCampo(linchenif, 0..3)
            val vArea = leggiCampo(areaCoin, 0..6)

            if (valido && vEritema != null && vEdema != null && vEscoriazione != null && vLinchenif != null && vArea != null) {
                val calcolo =
                    ((vEritema + vEdema + vEscoriazione + vLinchenif) * vArea).toDouble()
                val easi = Risultato(distrettoTesto.first(), calcolo)
                binding.tvRisultatiEASI.text = "Il tuo EASI è: %.1f".format(easi)
                binding.tvLivelloEASI.text = Livello(easi)
            }
        }
    }
}

private fun leggiCampo(campo: EditText, range: IntRange): Int? {
    val testo = campo.text.toString()
    val v = testo.toIntOrNull()
    return if (v == null || v !in range) {
        campo.error = "Deve essere un numero tra ${range.first} e ${range.last}"
        null
    } else v
}
    private fun DistrettoValido(c: Char): Boolean =
        c in listOf('H', 'h', 'U', 'u', 'T', 't', 'L', 'l')

    private fun Risultato(distretto: Char, calcolo: Double): Double =
        when (distretto.uppercaseChar()) {
            'H' -> 0.1 * calcolo
            'U' -> 0.2 * calcolo
            'T' -> 0.3 * calcolo
            'L' -> 0.4 * calcolo
            else -> 0.0
        }

    private fun Livello(easi: Double): String = when {
        easi in 0.1..1.0 -> "Quasi assente"
        easi in 1.1..7.0 -> "Lieve"
        easi in 7.1..21.0 -> "Moderata"
        easi in 21.1..50.0 -> "Grave"
        easi > 50 -> "Molto grave"
        else -> "Assente"
    }