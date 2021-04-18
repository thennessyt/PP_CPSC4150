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
    public Account(){

    }

    public Account(String n, String e){
        name = n;
        email = e;
    }

    public String getName()
    {
        return name;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getEmail() {
        return email;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }
}
