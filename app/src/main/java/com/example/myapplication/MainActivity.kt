package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private  val RQ_SPEECH_REC = 1
    private lateinit var  btn_trans : ImageButton
    private lateinit var  txt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_trans = findViewById(R.id.btn_tr)
        txt = findViewById(R.id.txt)
        //txt.textDirection = View.TEXT_DIRECTION_ANY_RTL
        btn_trans.setOnClickListener {

            AskInput()


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RQ_SPEECH_REC && resultCode==Activity.RESULT_OK){
           val result :ArrayList<String>? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            txt.setText( result?.get(0).toString())
        }
    }
    private fun AskInput() {
        if(!SpeechRecognizer.isRecognitionAvailable(this)){
            Toast.makeText(this , "speech recognition is not available " , Toast.LENGTH_SHORT).show()
        } else{

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL ,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE , Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT , "say something")
            startActivityForResult(intent,RQ_SPEECH_REC)
        }


    }
}
