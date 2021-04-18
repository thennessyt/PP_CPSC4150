package cpsc4150.epitaph.models;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.File;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Account.class,
                parentColumns = "id",
                childColumns = "contributionID",
                onDelete = CASCADE)})
public class ImageContribution
{
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    protected int id;

    @NonNull
    @TypeConverters(ImageTypeConverters.class)
    @ColumnInfo(name = "image")
    protected Bitmap image;

    @NonNull
    @ColumnInfo(name = "account_id")
    protected int accountID;
}
