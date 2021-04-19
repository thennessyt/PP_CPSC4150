/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import cpsc4150.epitaph.R;
import cpsc4150.epitaph.fragments.SavedMemorialsFragment;
import cpsc4150.epitaph.models.Account;

public class SavedMemorialsActivity extends AppCompatActivity implements SavedMemorialsFragment.OnMemorialSelectedListener
{
    private int accountID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_memorials);

        //Get account ID
        Bundle extra = getIntent().getExtras();
        accountID = extra.getInt(Account.EXTRA_ACCOUNT_ID);
    }

    @Override
    public void onMemorialSelected(int memorialID)
    {
        // Send the memorial ID of the clicked button to DetailsActivity
        Intent intent = new Intent(this, MemorialViewActivity.class);
        intent.putExtra(MemorialViewActivity.EXTRA_MEMORIAL_ID, memorialID);
        intent.putExtra(Account.EXTRA_ACCOUNT_ID, accountID);
        startActivity(intent);
    }
}