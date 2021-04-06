package cpsc4150.hw2.epitaph;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Memorial
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "birth_year")
    private int byear;

    @NonNull
    @ColumnInfo(name = "death_year")
    private int dyear;

    @NonNull
    @ColumnInfo(name = "epitaph")
    private String epitaph;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "comment_settings")
    private String commentSettings;

    @NonNull
    @ColumnInfo(name = "contribution_settings")
    private String contributionSettings;

//    @NonNull
//    @ColumnInfo(name = "locations")
//    private vector<Location> locations;
//    TODO: FIGURE OUT WHAT TO DO WITH LOCATIONS
}
