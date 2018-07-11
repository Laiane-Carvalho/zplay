package zup.com.br.zplay.authentication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import zup.com.br.zplay.R;
import zup.com.br.zplay.activities.LoginActivity;
import zup.com.br.zplay.activities.MainActivity;

/**
 * Created by laianeoliveira on 10/07/18.
 */

public class GoogleAuth {
    private static final int RC_SIGN_IN = 901;
    private static final String TAG = "googleSignIn";
    private static final String TAAG = "firebaseSignin";
    Activity activity;
    FirebaseAuth auth;
    private GoogleSignInClient mGoogleSignInClient;

    public GoogleAuth(Activity activity) {
        this.activity = activity;
        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestIdToken( activity.getString( R.string.default_web_client_id ) )
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient( activity, gso );
    }

    public void statusGoogle() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent( activity, MainActivity.class );
            activity.startActivity( intent );
            activity.finish();
        }
    }

    public void connectInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult( signInIntent, RC_SIGN_IN );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            Task <GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent( data );
            try {
                GoogleSignInAccount account = task.getResult( ApiException.class );
                firebaseAuthWithGoogle( account );

            } catch (ApiException e) {
                Log.i( TAG, " failed signIn Google" + e );
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.i( TAAG, "authentication firebase google: " + account.getEmail() );

        final AuthCredential authCredential = GoogleAuthProvider.getCredential( account.getIdToken(), null );
        auth.signInWithCredential( authCredential ).addOnCompleteListener( activity, new OnCompleteListener <AuthResult>() {
            @Override
            public void onComplete(@NonNull Task <AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d( TAAG, "signInWithCredential:success" );
                    FirebaseUser user = auth.getCurrentUser();
                    toast( "Sucesso ao logar na conta: " + user.getEmail() );
                    goToMain();
                } else {
                    Log.d( TAAG, "signInWithCredential:failed error: " + task.getException() );
                    toast( "falha ao tentar logar: " + task.getException() );
                }
            }
        } );
    }

    private void goToMain() {
        Intent intent = new Intent( activity, MainActivity.class );
        activity.startActivity( intent );
        activity.finish();
    }

    public void outGoogle() {
        auth.signOut();
        toast( "Deslogando Google..." );
        returnLogin();
    }

    private void returnLogin() {
        Intent intent = new Intent( activity, LoginActivity.class );
        activity.startActivity( intent );
        activity.finish();
    }

    private void toast(String s) {
        Toast.makeText( activity, s, Toast.LENGTH_LONG ).show();
    }
}
