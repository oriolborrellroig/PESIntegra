package integra.pesintegra.Logic.Clases;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import android.util.Base64;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static java.util.Base64.*;

public class ImageBM {


    private String idImage;
    private String  data;

    public ImageBM(String imageId, Bitmap data) {
        this.idImage = imageId;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        data.compress(Bitmap.CompressFormat.PNG, 100, baos);
        this.data = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT );
    }

    public Bitmap getBitmapImage() {
        byte[] byteImage = Base64.decode(data, 0);
        return BitmapFactory.decodeByteArray(byteImage, 0,byteImage.length);
    }

    public String getImageString() {
        return this.data;
    }
}