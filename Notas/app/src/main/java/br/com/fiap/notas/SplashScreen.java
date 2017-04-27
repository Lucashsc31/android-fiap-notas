package br.com.fiap.notas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Classe Handler trabalha internamente com Threads
        //Primeiro parâmetro é a thread. O segundo, quanto tempo ela deve esperar
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Executa o método que inicia a tela de Login
                chamarLogin();
            }
        }, 1200);
    }

    private void chamarLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

