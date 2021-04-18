package cpsc4150.epitaph.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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
    @ColumnInfo(name = "image")
    protected File image;

    @NonNull
    @ColumnInfo(name = "account_id")
    protected int accountID;
}
