package cpsc4150.epitaph;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import cpsc4150.epitaph.models.AudioContribution;

@Dao
public interface AudioContributionDao
{
    @Query("SELECT * FROM AudioContribution WHERE id = :id")
    public AudioContribution getAudio(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAudio(AudioContribution contribution);
}
