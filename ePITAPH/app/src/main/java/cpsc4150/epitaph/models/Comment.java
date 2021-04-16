package cpsc4150.epitaph.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Contribution.class,
                parentColumns = "id",
                childColumns = "contributionID",
                onDelete = CASCADE)})
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