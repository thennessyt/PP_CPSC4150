package cpsc4150.epitaph;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cpsc4150.epitaph.models.Account;
import cpsc4150.epitaph.models.Comment;
import cpsc4150.epitaph.models.Contribution;
import cpsc4150.epitaph.models.Memorial;

@Database(entities = {Account.class, Memorial.class, Contribution.class, Comment.class}, version = 1)
public abstract class EpitaphDatabase extends RoomDatabase
{
    private static final String DATABASE_NAME = "epitaph.db";
    private static EpitaphDatabase mEpitaphDatabase;

    // Singleton
    public static EpitaphDatabase getInstance(Context context)
    {
        if (mEpitaphDatabase == null)
        {
            mEpitaphDatabase = Room.databaseBuilder(context, EpitaphDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return mEpitaphDatabase;
    }
}