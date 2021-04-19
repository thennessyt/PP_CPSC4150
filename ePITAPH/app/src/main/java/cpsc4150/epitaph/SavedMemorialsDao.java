/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

package cpsc4150.epitaph;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cpsc4150.epitaph.models.SavedMemorials;

@Dao
public interface SavedMemorialsDao
{
    @Query("SELECT * FROM SavedMemorials WHERE accountID = :id")
    public List<SavedMemorials> getSavedMemorials(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSavedMemorial(SavedMemorials savedMemorials);

}
