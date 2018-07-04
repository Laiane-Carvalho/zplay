package zup.com.br.zplay.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zup.com.br.zplay.MainActivity;
import zup.com.br.zplay.R;

public class SplashActivity extends AppCompatActivity {

    private static int TEMPO_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.ativarSplash();
    }

    private void ativarSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, TEMPO_SPLASH);
    }
}
