package cpsc4150.hw2.epitaph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

public class OpeningMenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_menu);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_opening_menu);

        if (fragment == null)
        {
            fragment = new OpeningMenuFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_opening_menu, fragment)
                    .commit();
        }
    }

    // ------------ BUTTON ON-CLICK
    //create a new memorial
    public void onClickCreate()
    {
        //Start CreateMemorialActivity
        Intent intent = new Intent(this, CreateMemorialActivity.class);
        startActivity(intent);
    }


    //visit memorial menu
    public void onClickVisit()
    {
        //Start VisitMenuActivity
        Intent intent = new Intent(this, VisitMenuActivity.class);
        startActivity(intent);
    }
}