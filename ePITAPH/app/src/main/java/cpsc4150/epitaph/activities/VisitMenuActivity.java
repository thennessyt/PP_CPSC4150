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
import android.view.View;

import cpsc4150.epitaph.R;
import cpsc4150.epitaph.fragments.VisitMenuFragment;
import cpsc4150.epitaph.models.Account;

public class VisitMenuActivity extends AppCompatActivity
{
    private int accountID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_menu);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_visit_menu);

        if (fragment == null)
        {
            fragment = new VisitMenuFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_visit_menu, fragment)
                    .commit();
        }

        Bundle extra = getIntent().getExtras();
        accountID = extra.getInt(Account.EXTRA_ACCOUNT_ID);
    }

    public void onSavedClick(View view)
    {
        //Start SavedMemorialsActivity
        Intent intent = new Intent(this, SavedMemorialsActivity.class);
        intent.putExtra(Account.EXTRA_ACCOUNT_ID, accountID);
        startActivity(intent);
    }

    public void onEnterClick(View view)
    {
        //Start CodeMemorialActivity
        Intent intent = new Intent(this, CodeMemorialActivity.class);
        intent.putExtra(Account.EXTRA_ACCOUNT_ID, accountID);
        startActivity(intent);
    }
}