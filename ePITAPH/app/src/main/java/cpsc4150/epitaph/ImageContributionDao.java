package cpsc4150.epitaph;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import cpsc4150.epitaph.models.ImageContribution;

@Dao
public interface ImageContributionDao
{
    @Query("SELECT * FROM ImageContribution WHERE id = :id")
    public ImageContribution getImage(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertImage(ImageContribution contribution);
}
