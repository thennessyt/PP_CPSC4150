package cpsc4150.epitaph;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Comment extends Contribution
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected int id;

    @NonNull
    @ColumnInfo(name = "likes")
    protected int likes;

    @NonNull
    @ColumnInfo(name = "text")
    protected String text;

    Comment(String text)
    {
        this.text = text;
        this.likes = 0;
    }
}
