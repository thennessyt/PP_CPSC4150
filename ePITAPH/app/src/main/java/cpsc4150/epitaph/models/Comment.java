package cpsc4150.epitaph.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Account.class,
                parentColumns = "id",
                childColumns = "contributionID",
                onDelete = CASCADE)})
public class Comment
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

    @NonNull
    @ColumnInfo(name = "account_id")
    protected int accountID;

    public Comment(String text)
    {
        this.text = text;
        this.likes = 0;
    }

    public void like()
    {
        this.likes++;
    }

    public void unlike()
    {
        this.likes--;
    }
}
