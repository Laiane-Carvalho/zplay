package zup.com.br.zplay.authentication;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Arrays;

import zup.com.br.zplay.activities.LoginActivity;
import zup.com.br.zplay.activities.MainActivity;
/**
 * Created by laianeoliveira on 10/07/18.
 */
public class FacebookAuth {
    private static final String TAG = "loginFacebook";
    private static final String TAAG = "facebookAccess";
    private Activity activity;
    private CallbackManager callbackManager;
    private FirebaseAuth auth;

    public FacebookAuth(Activity activity) {
        this.activity = activity;
        auth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();
    }

    public void status() {
        FirebaseUser user = auth.getCurrentUser();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (user != null && accessToken != null) {
            Intent intent = new Intent( activity, MainActivity.class );
            activity.startActivity( intent );
            activity.finish();
        }
    }
    public void connectFacebook() {
        LoginManager.getInstance().logInWithReadPermissions( activity, Arrays.asList( "public_profile" ) );
        LoginManager.getInstance().registerCallback( callbackManager, new FacebookCallback <LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i( TAG, "success verification facebook" );
                facebookAccessToken( loginResult.getAccessToken() );
            }
            @Override
            public void onCancel() {
                Log.i( TAG, "login firebase cancel" );
            }
            @Override
            public void onError(FacebookException error) {
                Log.i( TAG, "login firebaseerror: " + error );
            }
        } );
    }
    private void facebookAccessToken(AccessToken accessToken) {
        Log.d( TAAG, "facebookAccessToken" + accessToken );

        AuthCredential credential = FacebookAuthProvider.getCredential( accessToken.getToken() );
        auth.signInWithCredential( credential ).addOnCompleteListener( activity, new OnCompleteListener <AuthResult>() {
            @Override
            public void onComplete(@NonNull Task <AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    Log.w( "credentialFire", "sucess signIn credential firebase" + user );
                    goToMain();

                } else {
                    Log.w( "credentialFire", "error signin credential " + task.getException() );
                    toast( " Authentication failed: " + task.getException() );
                }
            }
        } );
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult( requestCode, resultCode, data );
    }

    public  void outFacebook() {
        auth.signOut();
        LoginManager.getInstance().logOut();
        toast( "Deslogando facebook" );
        returnLogin();
    }

    private void returnLogin() {
        Intent intent = new Intent( activity, LoginActivity.class );
        activity.startActivity( intent );
        activity.finish();
    }

    private void goToMain(){
        Intent intent = new Intent( activity,MainActivity.class );
        activity.startActivity( intent );
        activity.finish();
    }

    private void toast(String s) {
        Toast.makeText( activity, s, Toast.LENGTH_LONG ).show();
    }
}
