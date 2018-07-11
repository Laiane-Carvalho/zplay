package zup.com.br.zplay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.OnClick;
import zup.com.br.zplay.R;
import zup.com.br.zplay.authentication.FacebookAuth;
import zup.com.br.zplay.authentication.GoogleAuth;
import zup.com.br.zplay.enuns.TipoLogin;

public class LoginActivity extends SupportActivity {

    private FacebookAuth facebookAuth;
    private GoogleAuth googleAuth;

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
        facebookAuth = new FacebookAuth( this );
        googleAuth = new GoogleAuth( this );
    }

    @OnClick({R.id.btnLoginFacebook, R.id.btnLoginGoogle})
    public void btnLoginFacebookClick(View view) {

        if (view.getId() == R.id.btnLoginFacebook) {
            facebookAuth.connectFacebook();
        }
        if (view.getId() == R.id.btnLoginGoogle) {
            googleAuth.connectInGoogle();
        }
    }

    public static TipoLogin checkLoginFacebookOrGoogle() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if (user.getProviders().contains( "google.com" )) {
                return TipoLogin.GOOGLE;
            }
            if (user.getProviders().contains( "facebook.com" )){
                return TipoLogin.FACEBOOK;
            }
        }
        return TipoLogin.NENHUM;
    }

    @Override
    protected void onStart() {
        super.onStart();
        facebookAuth.status();
        googleAuth.statusGoogle();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        facebookAuth.onActivityResult( requestCode, resultCode, data );
        googleAuth.onActivityResult( requestCode, resultCode, data );
    }

    private void startAnimation() {
        this.btnLoginFacebook.startAnimation( AnimationUtils.loadAnimation( this, R.anim.btn_login_facebook ) );
        this.btnLoginGoogle.startAnimation( AnimationUtils.loadAnimation( this, R.anim.btn_login_google ) );
    }
}
