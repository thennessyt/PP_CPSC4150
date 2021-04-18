package cpsc4150.epitaph;


import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import cpsc4150.epitaph.models.Comment;

public interface CommentDao extends ContributionDao
{
    @Query("SELECT * FROM Comment WHERE contribution_id = :id")
    public Comment getComment(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertComment(Comment comment);
}
