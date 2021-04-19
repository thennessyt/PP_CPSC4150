/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

package cpsc4150.epitaph.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.activities.MemorialViewActivity;
import cpsc4150.epitaph.models.Account;
import cpsc4150.epitaph.models.Comment;
import cpsc4150.epitaph.models.Memorial;

public class MemorialViewFragment extends Fragment
{
    private int memorialID;
    private ImageView memorialImage;
    private TextView memorialEpitaph;
    private TextView memorialName;
    private TextView memorialDateRange;
    private TextView memorialDescription;
    private EpitaphDatabase db;

    public interface OnLikeClickedListener
    {
        void onLikeClick(int commentID);
    }

    public interface OnReportClickedListener
    {
        void onReportClick(int commentID);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle extra = getActivity().getIntent().getExtras();
        memorialID = extra.getInt(MemorialViewActivity.EXTRA_MEMORIAL_ID);

        memorialImage = getActivity().findViewById(R.id.memorialImage);
        memorialEpitaph = getActivity().findViewById(R.id.memorialEpitaph);
        memorialName = getActivity().findViewById(R.id.memorialName);
        memorialDateRange = getActivity().findViewById(R.id.memorialDateRange);
        memorialDescription = getActivity().findViewById(R.id.memorialDescription);

        db = EpitaphDatabase.getInstance(getActivity().getApplicationContext());
        Memorial memorial = db.memorialDao().getMemorial(memorialID);

        Bitmap image = db.imageContributionDao().getImageByMemorialID(memorialID).getImage();
        Drawable drawable = new BitmapDrawable(getActivity().getResources(), image);
        memorialImage.setImageDrawable(drawable);
        //Set epitaph
        memorialEpitaph.setText(memorial.getEpitaph());
        //Set name of deceased
        memorialName.setText(memorial.getName());
        //Set birth to death range
        memorialDateRange.setText(memorial.getByear() + " - " + memorial.getDyear());
        //Set description
        memorialDescription.setText(memorial.getDescription());
        //TODO: COMMENTS RECYCLER PROBABLY
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memorial_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_memorial_view_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Send comments to recycler view
        List<Comment> list = db.commentDao().getCommentsByMemorialID(memorialID);
        MemorialViewFragment.CommentAdapter adapter = new MemorialViewFragment.CommentAdapter(list);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private class CommentHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        private Comment comment;

        private TextView commentAccount;
        private TextView commentText;

        public CommentHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.list_item_comment, parent, false));
            itemView.setOnClickListener(this);
            commentAccount = itemView.findViewById(R.id.commentAccount);
            commentText = itemView.findViewById(R.id.commentText);
        }

        public void bind(Comment comment)
        {
            Account account = db.accountDao().getAccount(comment.accountID);
            this.comment = comment;
            commentAccount.setText(account.getName());
            commentText.setText(comment.text);
        }

        @Override
        public void onClick(View view)
        {

        }
    }

    private class CommentAdapter extends RecyclerView.Adapter<MemorialViewFragment.CommentHolder>
    {
        private List<Comment> comments;

        public CommentAdapter(List<Comment> comments)
        {
            this.comments = comments;
        }

        @Override
        public MemorialViewFragment.CommentHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MemorialViewFragment.CommentHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MemorialViewFragment.CommentHolder holder, int position)
        {
            Comment comment = comments.get(position);
            holder.bind(comment);
        }

        @Override
        public int getItemCount()
        {
            return comments.size();
        }
    }
}