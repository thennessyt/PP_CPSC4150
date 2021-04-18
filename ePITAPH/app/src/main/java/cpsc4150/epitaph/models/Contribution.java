package cpsc4150.epitaph.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import static androidx.room.ForeignKey.CASCADE;

import java.time.LocalDateTime;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Memorial.class,
                parentColumns = "id",
                childColumns = "memorialID",
                onDelete = CASCADE),

        @ForeignKey(
                entity = Account.class,
                parentColumns = "id",
                childColumns = "accountID",
                onDelete = CASCADE)})
public class Contribution
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected int id;

    @NonNull
    @ColumnInfo(name = "memorialID")
    protected int memorialID;

    @NonNull
    @ColumnInfo(name = "accountID")
    protected int accountID;

    @NonNull
    @ColumnInfo(name = "status")
    protected String status;

    @NonNull
    @TypeConverters(DateTimeTypeConverters.class)
    @ColumnInfo(name = "datetime")
    protected LocalDateTime datetime;
}
