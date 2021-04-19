/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

package cpsc4150.epitaph.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import cpsc4150.epitaph.models.LocationVectorTypeConverters;

import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Vector;

@Entity
public class Memorial
{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected int id;


    @ColumnInfo(name = "is_this_new")
    public Boolean isThisNew = true;

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
    private Boolean commentSettings;

    @NonNull
    @ColumnInfo(name = "contribution_settings")
    private Boolean contributionSettings;

    @NonNull
    @TypeConverters(LocationVectorTypeConverters.class)
    @ColumnInfo(name = "locations")
    private Vector<Location> locations;
    //uses the Android location library so hopefully there won't be conversion issues

    public Memorial()
    {

    }

    //TODO: pre/post, actual most of this cosntructor
    public Memorial(String n, int by, int dy, String e, String d, Boolean comS, Boolean conS,
                    Vector<Location> locs)
    {
        name = n;
        byear = by;
        dyear = dy;
        epitaph = e;
        description = d;
        commentSettings = comS;
        contributionSettings = conS;
    }

    //------------------------ GETTER METHODS ------------------------
    public int getByear()
    {
        return byear;
    }

    public int getDyear()
    {
        return dyear;
    }

    public int getId()
    {
        return id;
    }

    @NonNull
    public Boolean getCommentSettings()
    {
        return commentSettings;
    }

    @NonNull
    public Boolean getContributionSettings()
    {
        return contributionSettings;
    }

    @NonNull
    public String getDescription()
    {
        return description;
    }

    public String getName()
    {
        return name;
    }

    @NonNull
    public String getEpitaph()
    {
        return epitaph;
    }

    public Vector<Location> getLocations()
    {
        return locations;
    }

    //------------------------ SETTER METHODS ------------------------


    public void setByear(int byear) {
        this.byear = byear;
    }


    public void setCommentSettings(@NonNull Boolean commentSettings)
    {
        this.commentSettings = commentSettings;
    }

    public void setDescription(@NonNull String description)
    {
        this.description = description;
    }

    public void setContributionSettings(@NonNull Boolean contributionSettings)
    {
        this.contributionSettings = contributionSettings;
    }

    public void setDyear(int dyear)
    {
        this.dyear = dyear;
    }

    public void setEpitaph(@NonNull String epitaph)
    {
        this.epitaph = epitaph;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocations(@NonNull Vector<Location> locations)
    {
        this.locations = locations;
    }

    public Boolean getThisNew() {
        return isThisNew;
    }

    public void setThisNew(Boolean thisNew) {
        isThisNew = thisNew;
    }

    public void setName(@NonNull String name)
    {
        this.name = name;
    }

    public void isNotNew() { this.isThisNew = false; }

}