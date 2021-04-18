/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

package cpsc4150.epitaph;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cpsc4150.epitaph.models.Comment;

@Dao
public interface CommentDao
{
    @Query("SELECT * FROM Comment WHERE id = :id")
    public Comment getComment(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertComment(Comment comment);

    @Query("SELECT * FROM Comment WHERE account_id = :id AND visible = 1")
    public List<Comment> getCommentsByMemorialID(int id);
}
