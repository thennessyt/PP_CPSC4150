package cpsc4150.hw2.epitaph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VisitMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}