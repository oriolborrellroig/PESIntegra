package integra.pesintegra.Logic.Clases;

import android.graphics.Bitmap;
import android.net.Uri;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImageBM {

    MultipartBody.Part bitmapImage;
    String uri;

    public ImageBM(String imageId, MultipartBody.Part requestFile) {
        this.uri = imageId;
        bitmapImage = requestFile;

    }

    public MultipartBody.Part getBitmapImage() {
        return this.bitmapImage;
    }
}