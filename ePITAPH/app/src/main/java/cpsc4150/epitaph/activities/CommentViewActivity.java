package cpsc4150.epitaph.activities;

import android.os.Bundle;
import android.widget.Toast;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.fragments.CommentViewFragment;
import cpsc4150.epitaph.fragments.MemorialViewFragment;
import cpsc4150.epitaph.models.Account;
import cpsc4150.epitaph.models.Comment;


import androidx.appcompat.app.AppCompatActivity;

public class CommentViewActivity extends AppCompatActivity implements CommentViewFragment.OnLikeClickedListener, CommentViewFragment.OnReportClickedListener
{
    private int memorialID;
    private int accountID;
    private EpitaphDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_view);

        //Get memorial and account ID, get DB instance
        Bundle extra = getIntent().getExtras();
        memorialID = extra.getInt(MemorialViewActivity.EXTRA_MEMORIAL_ID);
        accountID = extra.getInt(Account.EXTRA_ACCOUNT_ID);
        db = EpitaphDatabase.getInstance(getApplicationContext());
    }

    //Like a comment
    public void onLikeClick(int commentID)
    {
        Comment comment = db.commentDao().getComment(commentID);
        comment.like();
        Toast.makeText(this, "Liked!", Toast.LENGTH_LONG).show();
    }

    //Report a comment
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