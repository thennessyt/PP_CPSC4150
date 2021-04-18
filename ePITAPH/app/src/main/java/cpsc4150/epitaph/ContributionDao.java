package cpsc4150.epitaph;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import cpsc4150.epitaph.models.Contribution;

public interface ContributionDao
{
    @Query("SELECT * FROM Contribution WHERE id = :id")
    public Contribution getContribution(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertContribution(Contribution contribution);
}
