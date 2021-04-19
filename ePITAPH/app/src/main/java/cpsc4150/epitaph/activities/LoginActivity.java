/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

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
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.fragments.LoginFragment;
import cpsc4150.epitaph.models.Account;

//DOCUMENTATION USED: https://developers.google.com/identity/sign-in/android/sign-in?authuser=5

public class LoginActivity extends AppCompatActivity
{
    private EpitaphDatabase db;

    GoogleSignInClient mGoogleSignInClient;
    //sign in request code
    private static final int RC_SIGN_IN = 9001;
    final String TOKEN = "1053227532799-egp9hlnnn83eobojh90e5ggnpejab9ok.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(TOKEN)
                .requestEmail()
                .requestProfile()
                .build();

        SignInButton sib_google = findViewById(R.id.sib_google);

        sib_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignInClick(findViewById(R.id.sib_google));
            }
        });

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //initialize db
        db = EpitaphDatabase.getInstance(getApplicationContext());

        /*
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_login);

        if (fragment == null)
        {
            fragment = new LoginFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_login, fragment)
                    .commit();
        }*/

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount gaccount = GoogleSignIn.getLastSignedInAccount(this);
        //if it's not null, meaning someone is already signed in
        if(!(Objects.isNull(gaccount))){
            //go to the opening menu
            Log.e("caterpillar","Signed in previously! Moving on...");
            //Start Opening Menu Activity
            Account currAccount= db.accountDao().getAccount(gaccount.getEmail());
            Intent intent = new Intent(this, OpeningMenuActivity.class);
            intent.putExtra(Account.EXTRA_ACCOUNT_ID, currAccount.getId());
            startActivity(intent);
        }
    }

    public void onSignInClick(View v)
    {
        Log.e("caterpillar","IN SIGNIN");
        signIn();
    }

    private void signIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("caterpillar","GOT RESULT");

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            System.out.println("RESULT WAS SIGNIN");
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }

    //this is where they sign in
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        Log.e("caterpillar","TASK COMPLETE");
        try {
            GoogleSignInAccount gaccount = completedTask.getResult(ApiException.class);

            Account currAccount;
            // Signed in successfully, show authenticated UI.
            //check database for existing account
            try{

                Log.e("caterpillar","IN TRY");
                currAccount = db.accountDao().getAccount(gaccount.getEmail());
                if (Objects.isNull(currAccount))
                {

                    currAccount = new Account(gaccount.getGivenName() + " " +
                            gaccount.getFamilyName(), gaccount.getEmail());

                    db.accountDao().insertAccount(currAccount);

                }
            } catch (Exception e){
                Log.e("caterpillar","IN CATCH");

                currAccount = new Account(gaccount.getGivenName() + " " +
                        gaccount.getFamilyName(), gaccount.getEmail());

                db.accountDao().insertAccount(currAccount);

            }

            currAccount= db.accountDao().getAccount(gaccount.getEmail());

            Intent intent = new Intent(this, OpeningMenuActivity.class);
            intent.putExtra(Account.EXTRA_ACCOUNT_ID, currAccount.getId());
            startActivity(intent);
            //TODO: move to opening menu with account
        }
        catch (ApiException e)
        {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("caterpillar","signInResult:failed code=" + e.getStatusCode());
            //what do we do if sign-in fails
            //TODO:what
        }
    }

}