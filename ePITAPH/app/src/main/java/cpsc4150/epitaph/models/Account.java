package cpsc4150.epitaph.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Account
{
    public static final String EXTRA_ACCOUNT_ID = "cpsc4150.epitaph.account_id";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    //google account?

    public String getName()
    {
        return name;
    }
}
