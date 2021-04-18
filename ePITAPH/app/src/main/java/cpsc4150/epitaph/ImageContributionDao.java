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

import cpsc4150.epitaph.models.ImageContribution;

@Dao
public interface ImageContributionDao
{
    @Query("SELECT * FROM ImageContribution WHERE id = :id")
    public ImageContribution getImage(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertImage(ImageContribution contribution);
}
