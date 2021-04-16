package cpsc4150.epitaph.models;

import android.media.MediaPlayer;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Contribution.class,
                parentColumns = "id",
                childColumns = "contributionID",
                onDelete = CASCADE)})
public class AudioContribution
{
    @NonNull
    @ColumnInfo(name = "contribution_id")
    protected int contributionID;

    @NonNull
    @ColumnInfo(name = "audio")
    protected MediaPlayer audio;

}
