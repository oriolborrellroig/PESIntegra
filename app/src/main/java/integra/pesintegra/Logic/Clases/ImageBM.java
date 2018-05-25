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


    private String idOwner;
    private String ownerType;
    private String  base64Image;

    public ImageBM(String imageId, String ownerType, Bitmap data) {
        this.idOwner = imageId;
        this.ownerType = ownerType;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        data.compress(Bitmap.CompressFormat.PNG, 100, baos);
        base64Image = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT );
    }

    public Bitmap getBitmapImage() {
        byte[] byteImage = Base64.decode(base64Image, 0);
        return BitmapFactory.decodeByteArray(byteImage, 0,byteImage.length);
    }
}