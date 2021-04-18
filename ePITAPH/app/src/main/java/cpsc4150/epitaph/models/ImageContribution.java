package cpsc4150.epitaph.models;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

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
public class ImageContribution
{
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @TypeConverters(ImageTypeConverters.class)
    @ColumnInfo(name = "image")
    public Bitmap image;

    @NonNull
    @ColumnInfo(name = "account_id")
    public int accountID;

    @NonNull
    @ColumnInfo(name = "memorial_id")
    public int memorialID;

    public ImageContribution(Bitmap image, int accountID, int memorialID)
    {
        this.image = image;
        this.accountID = accountID;
        this.memorialID = memorialID;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public Bitmap getImage() {
        return image;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getMemorialID() {
        return memorialID;
    }
}
