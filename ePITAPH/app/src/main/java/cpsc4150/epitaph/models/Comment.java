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
    public int id;

    @NonNull
    @ColumnInfo(name = "likes")
    public int likes;

    @NonNull
    @ColumnInfo(name = "text")
    public String text;

    @NonNull
    @ColumnInfo(name = "account_id")
    public int accountID;

    //compiler demands it
    public int contributionID;

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
