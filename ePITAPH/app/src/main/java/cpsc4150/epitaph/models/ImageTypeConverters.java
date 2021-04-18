/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

package cpsc4150.epitaph.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.Image;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.Vector;


//import java.awt.image.BufferedImage;

public class ImageTypeConverters
{


    @TypeConverter
    public static Bitmap byteArrayToImage(byte[] data)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data , 0, data.length);

        return bitmap;
    }

    @TypeConverter
    public static byte[] imageToByteArray(Bitmap bmp)
    {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }
}