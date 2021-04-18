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
    public int id;

    @NonNull
    @ColumnInfo(name = "memorialID")
    public int memorialID;

    @NonNull
    @ColumnInfo(name = "accountID")
    public int accountID;

    @NonNull
    @ColumnInfo(name = "status")
    public String status;

    @NonNull
    @TypeConverters(DateTimeTypeConverters.class)
    @ColumnInfo(name = "datetime")
    public LocalDateTime datetime;

    //compiler demands an empty one
    public Contribution()
    {

    }

    public Contribution(Contribution contribution)
    {
        this.id = contribution.id;
        this.memorialID = contribution.memorialID;
        this.accountID = contribution.accountID;
        this.status = contribution.status;
        this.datetime = contribution.datetime;
    }

    public Contribution(int memorialID, int accountID, String status, LocalDateTime dt)
    {
        this.memorialID = memorialID;
        this.accountID = accountID;
        this.status = status;
        this.datetime = dt;
    }

    public int getId()
    {
        return id;
    }

    public int getAccountID()
    {
        return accountID;
    }

    public int getMemorialID()
    {
        return memorialID;
    }

    @NonNull
    public LocalDateTime getDatetime()
    {
        return datetime;
    }

    @NonNull
    public String getStatus()
    {
        return status;
    }
}
