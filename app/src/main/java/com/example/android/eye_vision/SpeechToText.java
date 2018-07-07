package com.example.android.eye_vision;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToText extends AppCompatActivity {
        TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        text = (TextView)findViewById(R.id.text);
    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.micImage)
        {
           // TextView result = (TextView)findViewById(R.id.text);
            promptSpeechInput();
        }
    }
    public  void  promptSpeechInput() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something");


        try {
            startActivityForResult(i, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(SpeechToText.this, "Sorry, No support ", Toast.LENGTH_LONG).show();
        }
    }





    public void onActivityResult(int request_code, int result_code, Intent i)
    {

        super.onActivityResult(request_code, result_code, i);
        switch (request_code)
        {
            case 100: if(result_code == RESULT_OK && i != null)
            {
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                text.setText(result.get(0));
            }
        }
    }





}
