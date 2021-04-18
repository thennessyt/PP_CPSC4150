package cpsc4150.epitaph.models;

import android.location.Location;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Vector;

public class LocationVectorTypeConverters
{


    @TypeConverter
    public static Vector<Location> stringToLocationVector(String data)
    {
        Gson gson = new Gson();

        Vector<Location> returnLocations = new Vector<Location>();
        if (data == null) {
            return returnLocations;
        }

        Type listType = new TypeToken<Vector<Location>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String locationsVectorToString(Vector<Location> locationsVector)
    {
        Gson gson = new Gson();
        return gson.toJson(locationsVector);
    }
}