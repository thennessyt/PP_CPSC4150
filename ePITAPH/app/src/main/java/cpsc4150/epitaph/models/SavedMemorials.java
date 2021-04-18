package cpsc4150.epitaph.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

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
public class SavedMemorials
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "memorialID")
    protected int memorialID;

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "accountID")
    protected int accountID;
}
