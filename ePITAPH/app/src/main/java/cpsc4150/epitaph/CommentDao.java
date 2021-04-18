package cpsc4150.epitaph;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import cpsc4150.epitaph.models.Comment;

@Dao
public interface CommentDao
{
    @Query("SELECT * FROM Comment WHERE id = :id")
    public Comment getComment(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertComment(Comment comment);
}
