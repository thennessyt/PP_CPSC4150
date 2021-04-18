package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.location.*;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import java.util.Vector;

import cpsc4150.epitaph.fragments.CreateMemorialFragment;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.models.Memorial;

public class CreateMemorialActivity extends AppCompatActivity {


    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memorial);

        //location thing
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_create_memorial);

        if (fragment == null) {
            fragment = new CreateMemorialFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_create_memorial, fragment)
                    .commit();
        }
    }


    //create the memorial
    public void onClickCreateMemorial(View view) {
        //here is a list of the objects to get things from
        //et_name : name of loved one
        EditText et_name = findViewById(R.id.et_name);
        //et_birthyear : birth year, 4 digits
        EditText et_birthyear = findViewById(R.id.et_birthyear);
        //et_deathyear : death year, 4 digits
        EditText et_deathyear = findViewById(R.id.et_deathyear);
        //rg_gender : radio button group
        //rb_male
        RadioButton rb_male = findViewById(R.id.rb_male);
        //rb_female
        RadioButton rb_female = findViewById(R.id.rb_female);
        //rb_nonbinary
        RadioButton rb_nonbinary = findViewById(R.id.rb_nonbinary);
        //et_epitaph : memorial tagline
        EditText et_epitaph = findViewById(R.id.et_epitaph);
        //et_description : memorial description
        EditText et_description = findViewById(R.id.et_description);
        //TODO: image uploading & taking ..... that will be used as the image button though
        //ib_memorialimage
        //TODO: gotta do camera stuff first but figure this out man . it'll go in as
        //TODO: a contribution object
        //cb_private : is it private (CODE ACCESS ONLY)
        CheckBox cb_private = findViewById(R.id.cb_private);
        //rg_commentpermissions : what level of approval is it at
        //rb_nocomment
        RadioButton rb_nocomments = findViewById(R.id.rb_nocomments);
        //rb_myapproval
        RadioButton rb_myapproval = findViewById(R.id.rb_myApproval);
        //rb_open
        RadioButton rb_open = findViewById(R.id.rb_open);
        //rg_contributionpermissions : what level of approval is it at
        //rb_nocontributions
        RadioButton rb_nocontributions = findViewById(R.id.rb_nocontributions);
        //rb_myapprovalcontributions
        RadioButton rb_myapprovalcontributions = findViewById(R.id.rb_myApprovalcontributions);
        //rb_opencontributions
        RadioButton rb_opencontributions = findViewById(R.id.rb_opencontributions);
        //cb_mylocation : do I need to get their location?
        CheckBox cb_mylocation = findViewById(R.id.cb_mylocation);

        //et_location : a city........ just one? let's see if we can bring up some special dialogue
        //instead of using an ET
        //TODO: edit text of location? is there a location library we can bring up that looks
        //TODO: up city names? im tired

        String n = et_name.toString();
        int by = Integer.parseInt(et_birthyear.toString());
        int dy = Integer.parseInt(et_deathyear.toString());
        String e = et_epitaph.toString();
        String d = et_description.toString();
        String comS = "";
        String conS = "";
        Vector<Location> locs = new Vector<Location>();
        //if their location is checked
        if (cb_mylocation.isChecked())
        {
            //if we don't have location permission ................. that sucks
            //TODO: what do we do if they say no? also listen to the todo inside that function \v
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            else
            {
                Task<Location> mylocTask = fusedLocationClient.getLastLocation();
                Location myLoc = mylocTask.getResult();
                //add their location
                locs.add(myLoc);
            }

        }


        //MEMORIAL CONSTRUCTOR:
        //Memorial(String n, int by, int dy, String e, String d, String comS, String conS,
        //Vector< Location > locs)
        //TODO: maybe location CAN be empty? it won't be searchable but thats okay
        Memorial myMem = new Memorial(n, by, dy, e, d, comS, conS, locs);

        //todo: make memorial object, next page after making memorial (confirmation that gives code)
    }
}