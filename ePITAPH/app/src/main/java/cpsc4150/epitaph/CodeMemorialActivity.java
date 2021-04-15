package cpsc4150.epitaph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class CodeMemorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_memorial);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_code_memorial);

        if (fragment == null)
        {
            fragment = new OpeningMenuFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_code_memorial, fragment)
                    .commit();
        }
    }
}