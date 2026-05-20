package com.example.progdispmobilieaaz

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.progdispmobilieaaz.databinding.ActivityBsaBinding
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt

class BSA : AppCompatActivity() {
    private lateinit var binding: ActivityBsaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBsaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.btnIndietroBSA.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnInfo.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Formule")
            builder.setMessage("Mosteller\nLa più usata per la sua praticità; ideale per calcoli rapidi e precisi sia su adulti che bambini.\n\n" +
                    "Du Bois\nLo standard classico; usata storicamente per stabilire i dosaggi clinici generali.\n\n" +
                    "Haycock\nSpecifica per la pediatria; ottimizzata per le proporzioni corporee dei neonati e dei ragazzi in crescita.\n\n" +
                    "Gehan & George\nSpecializzata per l'oncologia; garantisce precisione statistica nel dosaggio dei chemioterapici.\n\n" +
                    "Boyd\nUsata per casi particolari; più precisa per chi ha corporature insolite o estreme.")
            builder.setPositiveButton("X"){
                    dialog, _ -> dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()

            dialog.setCanceledOnTouchOutside(true)
        }
        binding.btnCalcolaBSA.setOnClickListener {
            calcoloBSA()
        }
    }

    private fun calcoloBSA()
    {
        var altezza = binding.PickerAltezzaBSA.text.toString().toDoubleOrNull()
        val peso = binding.PickerPesoBSA.text.toString().toDoubleOrNull()

        if (altezza == null) {
            binding.PickerAltezzaBSA.error = "Inserisci l'altezza"
        }
        if (peso == null) {
            binding.PickerPesoBSA.error = "Inserisci il peso"
        }
        if (peso != null && altezza != null){
            binding.tvRisultatiMonsteller.text = "Monsteller: %.3f m2".format(sqrt(((altezza*peso) / 3600)))
            binding.tvRisultatiDubois.text = "Du bois: %.3f m2".format(0.007184 * altezza.pow( 0.725) * peso.pow(0.425) )
            binding.tvRisultatiHaycock.text = "Haycock: %.3f m2".format(0.024265 * altezza.pow(0.3964) * peso.pow(0.5378))
            binding.tvRisultatiGehangeorge.text = "Gehan and George: %.3f m2".format(0.0235 * altezza.pow(0.42246) * peso.pow(0.51456))
            binding.tvRisultatiBoyd.text = "Boyd: %.3f m2".format((0.0003207*altezza.pow(0.3)) * (peso * 1000).pow((0.7285-(0.0188* log10(peso * 1000)))))
        }
    }
}