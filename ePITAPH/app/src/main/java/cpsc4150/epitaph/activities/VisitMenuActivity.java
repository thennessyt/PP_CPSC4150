package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cpsc4150.epitaph.R;
import cpsc4150.epitaph.fragments.VisitMenuFragment;

public class VisitMenuActivity extends AppCompatActivity
{
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
    }

    public void onSavedClick(View view)
    {
        //Start SavedMemorialsActivity
        Intent intent = new Intent(this, SavedMemorialsActivity.class);
        startActivity(intent);
    }

    public void onEnterClick(View view)
    {
        //Start CodeMemorialActivity
        Intent intent = new Intent(this, CodeMemorialActivity.class);
        startActivity(intent);
    }
}