package cpsc4150.epitaph;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import cpsc4150.epitaph.models.SavedMemorials;

@Dao
public interface SavedMemorialsDao
{
    @Query("SELECT * FROM SavedMemorials WHERE accountID = :id")
    public List<SavedMemorials> getSavedMemorials(int id);

}
