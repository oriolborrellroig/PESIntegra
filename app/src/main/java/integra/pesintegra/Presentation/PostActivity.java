package integra.pesintegra.Presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.R;

public class PostActivity extends Activity implements View.OnClickListener{

    Post post;
    private static final int SELECTED_PICTURE = 1;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Button btn_back = (Button)findViewById(R.id.btn_post_back);
        btn_back.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(this);
        iv = (ImageView) findViewById(R.id.imatge);
        final Button tres_punts = (Button) findViewById(R.id.tres_punts);
        tres_punts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(PostActivity.this, tres_punts);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                PostActivity.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method

        //Intent intent = getIntent();
       // String image_name = intent.getStringExtra("bitmap_img");

        this.post = (Post) getIntent().getExtras().getSerializable("post");
        TextView post_titol = (TextView) findViewById(R.id.post_titol);
        post_titol.setText(post.getTitol());
        TextView post_direccio = (TextView) findViewById(R.id.post_direccio);
        post_direccio.setText(post.getDireccio());
        TextView post_data = (TextView) findViewById(R.id.post_data);
        post_data.setText(post.getDataIni());
        TextView post_text = (TextView) findViewById(R.id.post_text);
        post_text.setText(post.getDescripcio());



        //DANI T HE COMENTAT TOT AIXO

        //Bitmap img_pre;
        //byte[] byteArray = getIntent().getByteArrayExtra("image");
        //img_pre = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        //iv.setImageBitmap(img_pre);

        // JAJA FINS AQUI



        /*
        if (la imatge del post no és nul·la){
            iv.setImageBitmap(Bitmap.createScaledBitmap(bitmap_del_post, iv.getMaxWidth(), iv.getMaxHeight(), false));
        }

        */

        TextView titol = (TextView)findViewById(R.id.post_titol);
        

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_post_back:
                this.finish();
                break;
            case R.id.fab:


                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case 52:
                if (resultCode == Activity.RESULT_OK) {

                    Uri selectedImage = intent.getData();
                    try {
                        int alcada = iv.getMaxHeight();
                        int amplada = iv.getMaxWidth();
                        Bitmap bitmapImage = decodeBitmap(selectedImage, alcada, amplada);
                        iv.setImageBitmap(bitmapImage);
                        //guardar imatge a la bd
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // Show the Selected Image on ImageView



                }
        }
    }
/*


 */
    public Bitmap decodeBitmap(Uri selectedImage, int alc, int ampl) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        if (alc/height_tmp > ampl/width_tmp){ //fer petit d'ample
            scale = scale*width_tmp/ampl;
        }else{ //fer petit d'alt
            scale = scale*height_tmp/alc;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
    }
}
