package cpsc4150.epitaph;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import cpsc4150.epitaph.models.Account;

@Dao
public interface AccountDao
{
    @Query("SELECT * FROM Account WHERE id = :id")
    public Account getAccount(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAccount(Account account);

    @Query("SELECT * FROM Account WHERE email = :e_mail")
    public Account getAccount(String e_mail);
}
