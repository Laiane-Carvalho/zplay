package zup.com.br.zplay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import butterknife.BindView;
import butterknife.OnClick;
import zup.com.br.zplay.R;

public class LoginActivity extends SupportActivity {

    @BindView(R.id.btnLoginFacebook)
    public View btnLoginFacebook;

    @BindView(R.id.btnLoginGoogle)
    public View btnLoginGoogle;

    @Override
    int layoutID() {
        return R.layout.activity_login;
    }

    @Override
    void inicializar(Bundle savedInstanceState) {
        startAnimation();
    }

    @OnClick({R.id.btnLoginFacebook, R.id.btnLoginGoogle})
    public void btnLoginFacebookClick() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void startAnimation() {
        this.btnLoginFacebook.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_login_facebook));
        this.btnLoginGoogle.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_login_google));
    }
}
