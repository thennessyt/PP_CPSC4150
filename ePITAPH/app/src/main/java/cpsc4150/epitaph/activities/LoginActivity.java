package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.fragments.OpeningMenuFragment;
import cpsc4150.epitaph.models.Account;


//DOCUMENTATION USED: https://developers.google.com/identity/sign-in/android/sign-in?authuser=5


public class LoginActivity extends AppCompatActivity {


    private EpitaphDatabase db;

    GoogleSignInClient mGoogleSignInClient;
    //sign in request code
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //initialize db
        db = EpitaphDatabase.getInstance(getApplicationContext());

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_login);

        if (fragment == null)
        {
            fragment = new LoginFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_login, fragment)
                    .commit();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //if it's not null, meaning someone is already signed in
        if(!(Objects.isNull(account))){
            //go to the opening menu
            //Start Opening Menu Activity
            //TODO: move to opening menu with account
            Intent intent = new Intent(this, OpeningMenuActivity.class);
            intent.putExtra(Account.EXTRA_ACCOUNT_ID, //TODO: get the right integer from the database);
            startActivity(intent);
        }
    }

    public void onSignInClick(View v){
        signIn();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }

    //this is where they sign in
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount gaccount = completedTask.getResult(ApiException.class);

            Account currAccount;
            // Signed in successfully, show authenticated UI.
            //check database for existing account
            try{
                currAccount = db.accountDao().getAccount(gaccount.getEmail());
            } catch (Exception e){

                currAccount = new Account(gaccount.getGivenName() + " " +
                        gaccount.getFamilyName(), gaccount.getEmail());

                db.accountDao().insertAccount(currAccount);

            }
            //TODO: move to opening menu with account
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            System.out.println("signInResult:failed code=" + e.getStatusCode());
            //what do we do if sign-in fails
            //TODO:what
        }
    }

}