package cpsc4150.epitaph.models;

import android.location.Location;

import androidx.room.TypeConverter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Vector;

public class DateTimeTypeConverters
{


    @TypeConverter
    public static LocalDateTime stringToDateTime(String data)
    {

        LocalDateTime datetime = null;
        if (data == null || data == "") {
            return datetime;
        }

        return LocalDateTime.parse(data);
    }

    @TypeConverter
    public static String dateTimeToString(LocalDateTime datetime)
    {
        return datetime.toString();
    }
}