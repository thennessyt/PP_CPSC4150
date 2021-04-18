/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

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
                childColumns = "account_id",
                onDelete = CASCADE),
        @ForeignKey(
                entity = Memorial.class,
                parentColumns = "id",
                childColumns = "memorial_id",
                onDelete = CASCADE
        )})
public class Comment
{
    public static int REPORT_MAX = 3;

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

    @NonNull
    @ColumnInfo(name = "memorial_id")
    public int memorialID;

    @NonNull
    @ColumnInfo(name = "report")
    public int report;

    @NonNull
    @ColumnInfo(name = "visible")
    public boolean visible;

    public Comment(String text, int accountID, int memorialID)
    {
        this.text = text;
        this.likes = 0;
        this.accountID = accountID;
        this.memorialID = memorialID;
        this.report = 0;
    }

    public void like()
    {
        this.likes++;
    }

    public void report()
    {
        this.report++;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
}
