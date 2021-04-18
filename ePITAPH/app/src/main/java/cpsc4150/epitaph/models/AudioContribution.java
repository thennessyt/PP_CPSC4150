package cpsc4150.epitaph.models;

import android.media.MediaPlayer;

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
public class AudioContribution
{
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    protected int id;

    @NonNull
    @ColumnInfo(name = "audio")
    protected MediaPlayer audio;

    @NonNull
    @ColumnInfo(name = "account_id")
    public int accountID;

    @NonNull
    @ColumnInfo(name = "memorial_id")
    public int memorialID;
}
