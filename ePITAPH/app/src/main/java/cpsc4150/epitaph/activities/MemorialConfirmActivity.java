package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import cpsc4150.epitaph.R;
import cpsc4150.epitaph.fragments.MemorialConfirmFragment;

public class MemorialConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorial_confirm);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_memorial_confirm);

        if (fragment == null)
        {
            fragment = new MemorialConfirmFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_memorial_confirm, fragment)
                    .commit();
        }
    }

    //go to the specified memorial
    //TODO: i forgot how to do this
    public void onGotoMemorialClick(View v)
    {

    }
}