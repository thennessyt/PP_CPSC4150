package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import cpsc4150.epitaph.fragments.CreateMemorialFragment;
import cpsc4150.epitaph.R;

public class CreateMemorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memorial);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_create_memorial);

        if (fragment == null)
        {
            fragment = new CreateMemorialFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_create_memorial, fragment)
                    .commit();
        }
    }


    //create the memorial
    public void onClickCreateMemorial(View view)
    {
        //here is a list of the objects to get things from
        //et_name : name of loved one
        //et_birthyear : birth year, 4 digits
        //et_deathyear : death year, 4 digits
        //rg_gender : radio button group
            //rb_male
            //rb_female
            //rb_nonbinary
        //et_epitaph : memorial tagline
        //et_description : memorial description
        //TODO: image uploading & taking ..... that will be used as the image button though
            //ib_memorialimage
        //cb_private : is it private (CODE ACCESS ONLY)
        //rg_commentpermissions : what level of approval is it at
            //rb_nocomment
            //rb_myapproval
            //rb_open
        //rg_contributionpermissions : what level of approval is it at
        //rb_nocontributions
        //rb_myapprovalcontributions
        //rb_opencontributions
        //cb_mylocation : do I need to get their location?
        //et_location : a city........ just one? let's see if we can bring up some special dialogue
                //instead of using an ET

        //MEMORIAL CONSTRUCTOR:
        //Memorial(String n, int by, int dy, String e, String d, String comS, String conS,
            //Vector< Location > locs)

        //todo: make memorial object, next page after making memorial (confirmation that gives code)
    }
}