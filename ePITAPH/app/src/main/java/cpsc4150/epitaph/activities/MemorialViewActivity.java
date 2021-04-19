/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

package cpsc4150.epitaph.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.fragments.MemorialViewFragment;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.models.Account;
import cpsc4150.epitaph.models.Comment;
import cpsc4150.epitaph.models.Memorial;
import cpsc4150.epitaph.models.SavedMemorials;

public class MemorialViewActivity extends AppCompatActivity implements MemorialViewFragment.OnLikeClickedListener, MemorialViewFragment.OnReportClickedListener
{
    public static final String EXTRA_MEMORIAL_ID = "cpsc4150.epitaph.memorial_id";
    private int memorialID;
    private int accountID;
    private EpitaphDatabase db;

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

        Bundle extra = getIntent().getExtras();
        memorialID = extra.getInt(MemorialViewActivity.EXTRA_MEMORIAL_ID);
        accountID = extra.getInt(Account.EXTRA_ACCOUNT_ID);
        db = EpitaphDatabase.getInstance(getApplicationContext());
    }

    public void onCopyClick(View view)
    {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", String.valueOf(memorialID));
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Memorial ID copied!", Toast.LENGTH_LONG).show();
    }

    public void addButtonClick(View view)
    {
        //Start CreateCommentActivity
        Intent intent = new Intent(this, VisitMenuActivity.class);
        intent.putExtra(MemorialViewActivity.EXTRA_MEMORIAL_ID, memorialID);
        intent.putExtra(Account.EXTRA_ACCOUNT_ID, accountID);
        startActivity(intent);
    }

    public void onSaveMemorialClick(View view)
    {
        SavedMemorials savedMemorials = new SavedMemorials(accountID, memorialID);
        db.savedMemorialsDao().insertSavedMemorial(savedMemorials);
        Toast.makeText(this, "Memorial saved!", Toast.LENGTH_LONG).show();
    }

    public void onLikeClick(int commentID)
    {
        Comment comment = db.commentDao().getComment(commentID);
        comment.like();
        Toast.makeText(this, "Liked!", Toast.LENGTH_LONG).show();
    }

    public void onReportClick(int commentID)
    {
        Comment comment = db.commentDao().getComment(commentID);
        comment.report();
        Toast.makeText(this, "Reported!", Toast.LENGTH_LONG).show();
        if(comment.report > Comment.REPORT_MAX)
        {
            comment.setVisible(false);
        }
    }
}