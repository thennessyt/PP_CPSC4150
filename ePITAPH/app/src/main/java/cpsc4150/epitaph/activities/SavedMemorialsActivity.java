package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import cpsc4150.epitaph.R;
import cpsc4150.epitaph.fragments.SavedMemorialsFragment;

public class SavedMemorialsActivity extends AppCompatActivity implements SavedMemorialsFragment.OnMemorialSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_memorials);
    }

    @Override
    public void onMemorialSelected(int memorialID)
    {
        // Send the band ID of the clicked button to DetailsActivity
        Intent intent = new Intent(this, MemorialViewActivity.class);
        intent.putExtra(MemorialViewActivity.EXTRA_MEMORIAL_ID, memorialID);
        startActivity(intent);
    }
}