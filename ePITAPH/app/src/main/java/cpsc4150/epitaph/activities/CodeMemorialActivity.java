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
import android.widget.EditText;
import android.widget.Toast;

import cpsc4150.epitaph.fragments.CodeMemorialFragment;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.models.Account;
import cpsc4150.epitaph.models.Memorial;

public class CodeMemorialActivity extends AppCompatActivity
{
    private EditText memorialCodeView;
    private int accountID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_memorial);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_code_memorial);

        if (fragment == null)
        {
            fragment = new CodeMemorialFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_code_memorial, fragment)
                    .commit();
        }

        //Get account ID
        Bundle extra = getIntent().getExtras();
        accountID = extra.getInt(Account.EXTRA_ACCOUNT_ID);

        memorialCodeView = findViewById(R.id.enterMemorialCodeEdit);
    }

    //Goes to the memorial of the ID entered
    public void onGotoClick(View view)
    {
        //Create intent to start MemorialViewActivity
        Intent intent = new Intent(this, MemorialViewActivity.class);

        if(memorialCodeView.getText().equals(""))
        {
            Toast.makeText(this, "Enter memorial code.", Toast.LENGTH_LONG).show();
        }
        else
        {
            //Put memorial code into intent
            intent.putExtra(MemorialViewActivity.EXTRA_MEMORIAL_ID, memorialCodeView.getText());
            intent.putExtra(Account.EXTRA_ACCOUNT_ID, accountID);
            startActivity(intent);
        }
    }
}