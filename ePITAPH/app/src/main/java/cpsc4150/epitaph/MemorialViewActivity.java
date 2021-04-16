package cpsc4150.epitaph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MemorialViewActivity extends AppCompatActivity
{

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