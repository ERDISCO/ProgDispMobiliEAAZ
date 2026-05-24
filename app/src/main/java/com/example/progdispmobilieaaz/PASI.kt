package com.example.progdispmobilieaaz

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.progdispmobilieaaz.databinding.ActivityPasiBinding

class PASI : AppCompatActivity() {
    private lateinit var binding: ActivityPasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasiBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val distretto = findViewById<EditText>(R.id.DistrettoPASI)
        val eritema = findViewById<EditText>(R.id.EritemaPASI)
        val indurimento = findViewById<EditText>(R.id.IndurimentoPASI)
        val desquamazione = findViewById<EditText>(R.id.DesquamazionePASI)
        val areaCoin = findViewById<EditText>(R.id.AreaCoinPASI)

        binding.btnIndietroPASI.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.PASICalculator.setOnClickListener {
            var valido = true
            val distrettoTesto = distretto.text.toString()

            if (distrettoTesto.isEmpty() || !DistrettoValido(distrettoTesto.first())) {
                distretto.error = "Inserire un distretto valido: H, U, T, L"
                valido = false
            }

            val vEritema = leggiCampo(eritema, 0..4)
            val vIndurimento = leggiCampo(indurimento, 0..4)
            val vDesquamazione = leggiCampo(desquamazione, 0..4)
            val vArea = leggiCampo(areaCoin, 0..6)

            if (valido && vEritema != null && vIndurimento != null && vDesquamazione != null && vArea != null) {
                val calcolo = ((vEritema + vIndurimento + vDesquamazione) * vArea).toDouble()
                val pasi = Risultato(distrettoTesto.first(), calcolo)

                binding.tvRisultatiPASI.text = "Il tuo PASI è: %.1f".format(pasi)
                binding.tvLivelloPASI.text = Livello(pasi)
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

private fun Livello(pasi: Double): String = when {
    pasi < 10.0 -> "Psoriasi lieve"
    pasi in 10.0..20.0 -> "Psoriasi moderata"
    pasi > 20.0 -> "Psoriasi grave"
    else -> "Errore dati"
}
