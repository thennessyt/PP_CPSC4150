package cpsc4150.epitaph;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import cpsc4150.epitaph.models.Memorial;

@Dao
public interface MemorialDao
{
    @Query("SELECT * FROM Memorial WHERE id = :id")
    public Memorial getMemorial(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMemorial(Memorial memorial);

    @Delete
    public void deleteMemorial(Memorial memorial);
}
