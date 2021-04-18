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
    //TODO: erroring because it inherits an ID from contribution: do we remove this one?
    //@PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name = "c_id")
    //protected int c_id;

    protected int contributionID;

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
