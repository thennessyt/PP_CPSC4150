package cpsc4150.epitaph.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.File;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Contribution.class,
                parentColumns = "id",
                childColumns = "contributionID",
                onDelete = CASCADE)})
public class ImageContribution extends Contribution
{
    @NonNull
    @ColumnInfo(name = "contribution_id")
    protected int contributionID;

    @NonNull
    @ColumnInfo(name = "image")
    protected File image;
}
