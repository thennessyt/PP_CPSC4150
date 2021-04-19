package cpsc4150.epitaph.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.activities.MemorialViewActivity;
import cpsc4150.epitaph.models.Account;
import cpsc4150.epitaph.models.Comment;

public class CommentViewFragment extends Fragment
{
    private EpitaphDatabase db;
    private int memorialID;

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
        db = EpitaphDatabase.getInstance(getActivity().getApplicationContext());
        Bundle extra = getActivity().getIntent().getExtras();
        memorialID = extra.getInt(MemorialViewActivity.EXTRA_MEMORIAL_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_comment_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Send comments to recycler view
        List<Comment> list = db.commentDao().getCommentsByMemorialID(memorialID);
        CommentViewFragment.CommentAdapter adapter = new CommentViewFragment.CommentAdapter(list);
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

    private class CommentAdapter extends RecyclerView.Adapter<CommentViewFragment.CommentHolder>
    {
        private List<Comment> comments;

        public CommentAdapter(List<Comment> comments)
        {
            this.comments = comments;
        }

        @Override
        public CommentViewFragment.CommentHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CommentViewFragment.CommentHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CommentViewFragment.CommentHolder holder, int position)
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