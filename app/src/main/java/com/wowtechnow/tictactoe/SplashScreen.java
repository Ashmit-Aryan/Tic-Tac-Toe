package com.wowtechnow.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progress_circular);
        Splash splash = new Splash();
        progressBar.setVisibility(View.VISIBLE);
        splash.start();
    }

    public class Splash extends Thread{
        public void run(){
            try {
                sleep(1000*3);
            }catch(Exception e){
                e.printStackTrace();
            }

            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            SplashScreen.this.finish();
        }
    }
}
