package com.example.proyecto01

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var tts : TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val texto = findViewById<EditText>(R.id.etNombre).text

        findViewById<Button>(R.id.btnIniciar).setOnClickListener {
            Log.i("Boton", "El nombre es:  $texto" )
            tts?.speak(texto, TextToSpeech.QUEUE_FLUSH,null,"")
        }

            tts = TextToSpeech(this,this)

    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            tts?.setLanguage(Locale.forLanguageTag("es"))
            findViewById<TextView>(R.id.textView).text="TextToSpeech Disponible"
        }else{
            findViewById<TextView>(R.id.textView).text="TextToSpeech no Disponible"
        }
    }
}