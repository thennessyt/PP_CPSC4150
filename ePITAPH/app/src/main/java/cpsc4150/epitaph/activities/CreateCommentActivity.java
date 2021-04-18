package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.fragments.CodeMemorialFragment;
import cpsc4150.epitaph.fragments.CreateCommentFragment;
import cpsc4150.epitaph.models.Account;
import cpsc4150.epitaph.models.Comment;

public class CreateCommentActivity extends AppCompatActivity
{
    private EditText commentEditText;
    private EpitaphDatabase db;
    private int memorialID;
    private int accountID;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_comment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_create_comment);

        if (fragment == null)
        {
            fragment = new CreateCommentFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_create_comment, fragment)
                    .commit();
        }

        db = EpitaphDatabase.getInstance(getApplicationContext());
        commentEditText = findViewById(R.id.et_comment);

        Bundle extra = getIntent().getExtras();
        memorialID = extra.getInt(MemorialViewActivity.EXTRA_MEMORIAL_ID);
        accountID = extra.getInt(Account.EXTRA_ACCOUNT_ID);
    }

    public void onCreateCommentClick(View view)
    {
        Comment comment = new Comment(commentEditText.getText().toString(), accountID, memorialID);
        db.commentDao().insertComment(comment);
        Toast.makeText(this, "Comment created!", Toast.LENGTH_LONG).show();
    }
}