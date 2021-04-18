package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import cpsc4150.epitaph.fragments.MemorialViewFragment;
import cpsc4150.epitaph.R;

public class MemorialViewActivity extends AppCompatActivity
{
    public static final String EXTRA_MEMORIAL_ID = "cpsc4150.epitaph.memorial_id";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorial_view);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_memorial_view);

        if (fragment == null)
        {
            fragment = new MemorialViewFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_memorial_view, fragment)
                    .commit();
        }
    }
}