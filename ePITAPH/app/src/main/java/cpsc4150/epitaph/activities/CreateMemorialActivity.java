/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

package cpsc4150.epitaph.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import android.location.*;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.fragments.CreateMemorialFragment;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.models.Account;
import cpsc4150.epitaph.models.Comment;
import cpsc4150.epitaph.models.ImageContribution;
import cpsc4150.epitaph.models.Memorial;

public class CreateMemorialActivity extends AppCompatActivity
{
    private FusedLocationProviderClient fusedLocationClient;

    Location myLoc = null;

    private EpitaphDatabase db;

    private final int REQUEST_TAKE_PHOTO = 1;

    private File mPhotoFile;
    private Bitmap bitmap;

    int accountID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memorial);

        //Initialized fused location provider
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //Initialize database
        db = EpitaphDatabase.getInstance(getApplicationContext());

        //Get account ID
        Bundle extra = getIntent().getExtras();
        accountID = extra.getInt(Account.EXTRA_ACCOUNT_ID);

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

    //If user selects from their location, get the location
    public void onClickMyLocation(View view)
    {
        //Make the submit button unclickable
        Button submit = findViewById(R.id.btn_creatememorial);
        submit.setClickable(false);
        submit.setText("Please wait...");
        //if we don't have location permission ................. that sucks
        //TODO: what do we do if they say no? also listen to the todo inside that function \v
        while (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            String[] permissions = new String[1];
            permissions[0] = Manifest.permission.ACCESS_COARSE_LOCATION;
            ActivityCompat.requestPermissions(this, permissions, 0);
            System.out.println("Requesting permissions...");
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            // return;
        }
        Task<Location> mylocTask = fusedLocationClient.getLastLocation();
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>()
                {
                    @Override
                    public void onSuccess(Location location)
                    {
                        if (location != null)
                        {
                            System.out.println("LOCATION RECEIVED");
                            myLoc = mylocTask.getResult();
                            // write code here to to make the API call to the weather service and update the UI
                            // this code here is running on the Main UI thread as far as I understand
                            //make submit button clickable
                            Button submit = findViewById(R.id.btn_creatememorial);
                            submit.setClickable(true);
                            submit.setText("CREATE MEMORIAL");
                        }
                        else
                        {
                            System.out.println("LOCATION FAILED");
                        }
                    }
                });
    }

    //Create the memorial when memorial create button clicked
    public void onClickCreateMemorial(View view)
    {
        //List of the objects to get views from
        //et_name : name of loved one
        EditText et_name = findViewById(R.id.et_name);
        //et_birthyear : birth year, 4 digits
        EditText et_birthyear = findViewById(R.id.et_birthyear);
        //et_deathyear : death year, 4 digits
        EditText et_deathyear = findViewById(R.id.et_deathyear);
        //et_epitaph : memorial tagline
        EditText et_epitaph = findViewById(R.id.et_epitaph);
        //et_description : memorial description
        EditText et_description = findViewById(R.id.et_description);
        //TODO: image uploading & taking ..... that will be used as the image button though
        //ib_memorialimage
        //TODO: gotta do camera stuff first but figure this out man . it'll go in as
        //TODO: a contribution object
        //rg_commentpermissions : what level of approval is it at
        //rb_nocomment
        RadioButton rb_nocomm = findViewById(R.id.rb_nocomments);
        //rb_open
        RadioButton rb_open = findViewById(R.id.rb_open);
        //rg_contributionpermissions : what level of approval is it at
        //rb_nocontributions
        RadioButton rb_nocontributions = findViewById(R.id.rb_nocontributions);
        //rb_opencontributions
        RadioButton rb_opencontributions = findViewById(R.id.rb_opencontributions);
        //cb_mylocation : do I need to get their location?
        CheckBox cb_mylocation = findViewById(R.id.cb_mylocation);

        //et_location : a city........ just one? let's see if we can bring up some special dialogue
        //instead of using an ET
        //TODO: edit text of location? is there a location library we can bring up that looks
        //TODO: up city names? im tired

        String n = et_name.getText().toString();
        int by = Integer.parseInt(et_birthyear.getText().toString());
        int dy = Integer.parseInt(et_deathyear.getText().toString());
        String e = et_epitaph.getText().toString();
        String d = et_description.getText().toString();
        //TODO: settings for comments
        Boolean comS = true;
        Boolean conS = true;
        Vector<Location> locs = new Vector<Location>();
        //if their location is checked and location isn't null
        if (cb_mylocation.isChecked() && !(Objects.isNull(myLoc)))
        {
            locs.add(myLoc);
        }
        //otherwise no location


        //MEMORIAL CONSTRUCTOR:
        //Memorial(String n, int by, int dy, String e, String d, String comS, String conS,
        //Vector< Location > locs)
        Memorial myMem = new Memorial(n, by, dy, e, d, comS, conS, locs);

        //Add memorial to database
        db.memorialDao().insertMemorial(myMem);

        Toast.makeText(this, "Memorial created!", Toast.LENGTH_LONG).show();

        ImageContribution imageContribution = new ImageContribution(bitmap, accountID, myMem.getId());
        db.imageContributionDao().insertImage(imageContribution);

        //Start MemorialViewActivity
        Intent intent = new Intent(this, MemorialViewActivity.class);
        intent.putExtra(MemorialViewActivity.EXTRA_MEMORIAL_ID, myMem.getId());
        intent.putExtra(Account.EXTRA_ACCOUNT_ID, accountID);
        startActivity(intent);

    }

    public void takePhotoClick(View view)
    {

        while (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            String[] permissions = new String[1];
            permissions[0] = Manifest.permission.CAMERA;
            ActivityCompat.requestPermissions(this, permissions, 0);
            System.out.println("Requesting Camera permissions...");
        }

        // Create implicit intent
        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (photoCaptureIntent.resolveActivity(getPackageManager()) != null)
        {
            // Create the File where the photo should go
            try
            {
                mPhotoFile = createImageFile();
            }
            catch (IOException ex)
            {
                // Error occurred while creating the File
                ex.printStackTrace();
            }

            // If the File was successfully created, start camera app
            if (mPhotoFile != null)
            {
                // Create content URI to grant camera app write permission to photoFile
                Uri photoUri = FileProvider.getUriForFile(this,
                        "cpsc4150.epitaph.fileprovider",
                        mPhotoFile);

                // Add content URI to intent
                photoCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                // Start camera app
                startActivityForResult(photoCaptureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK)
        {
            //Initialize bitmap to image taken
            String filePath = mPhotoFile.getPath();
            bitmap = BitmapFactory.decodeFile(filePath);
        }
    }

    private File createImageFile() throws IOException
    {
        // Create a unique image filename
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFilename = "photo_" + timeStamp + ".jpg";

        // Get file path where the app can save a private image
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(storageDir, imageFilename);
    }

}