package integra.pesintegra.Presentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Objects;

import integra.pesintegra.Controllers.ControladorDomini;
import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Controllers.ControladorPresentacioLoginActivity;
import integra.pesintegra.Controllers.ControladorPresentacioPostOpen;
import integra.pesintegra.Controllers.ControladorPresentacioProfileActivity;
import integra.pesintegra.Logic.Clases.Comentari;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.R;

public class PostActivity extends Activity implements View.OnClickListener{
    Post post;
    String post_id;
    private static final int SELECTED_PICTURE = 1;
    ImageView iv;
    private CoordinatorLayout coordinatorLayout;
    private ControladorPresentacioPostOpen cp;
    TextView votantsTotals;
    TextView avgScore;
    RatingBar scoreBar;
    private final LoginActivity li = new LoginActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cp = new ControladorPresentacioPostOpen(this, getApplicationContext());
        setContentView(R.layout.activity_post);
        FloatingActionButton join = findViewById(R.id.join);
        join.setOnClickListener(this);
        Button btn_back = findViewById(R.id.btn_post_back);
        btn_back.setOnClickListener(this);
        iv = findViewById(R.id.imatge);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        final Button tres_punts = findViewById(R.id.tres_punts);
        TextView post_direccio = findViewById(R.id.post_direccio);
        post_direccio.setOnClickListener(this);
        Button btn_enviar = findViewById(R.id.enviar);
        btn_enviar.setOnClickListener(this);



        tres_punts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(PostActivity.this, tres_punts);


                //Inflating the Popup using xml file
                //int r = li.getCurrentUser().isInHiddenList(post.getId());

                ///if(r < 0) ;
                //else popup.getMenuInflater().inflate(R.menu.popup_menu_hidden, popup.getMenu());
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                PostActivity.this,
                                R.string.msgYouClicked + item.getTitle().toString(),
                                Toast.LENGTH_SHORT
                        ).show();

                        switch (item.getItemId()){

                            case R.id.show_post:
                                //li.getCurrentUser().removeHiddenPost(post.getId());
                                new ControladorPresentacioPostOpen(PostActivity.this, getApplicationContext());
                                new ControladorPresentacioLoginActivity();
                                new ControladorPresentacioLoginActivity(ControladorPresentacioLoginActivity.getActivityLogin(), getApplicationContext());
                                ControladorPresentacioPostOpen.updateRemoveHiddenList(post.getId()
                                        , ControladorPresentacioLoginActivity.getUserSessio());
                                Snackbar snackbar = Snackbar
                                        .make(coordinatorLayout, R.string.confShowPostAgain, Snackbar.LENGTH_LONG)
                                        .setAction(R.string.undo, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //li.getCurrentUser().addHiddenPost(post.getId());
                                                new ControladorPresentacioPostOpen(PostActivity.this, getApplicationContext());
                                                new ControladorPresentacioLoginActivity();
                                                new ControladorPresentacioLoginActivity(ControladorPresentacioLoginActivity.getActivityLogin(), getApplicationContext());
                                                ControladorPresentacioPostOpen.updateAddHiddenList(post.getId()
                                                        , ControladorPresentacioLoginActivity.getUserSessio());
                                                Snackbar snackbar2 = Snackbar.make(coordinatorLayout, "Request canceled!", Snackbar.LENGTH_SHORT);
                                                snackbar2.show();
                                            }
                                        });
                                snackbar.show();
                                break;
                            case R.id.hide_post:
                                //li.getCurrentUser().addHiddenPost(post.getId());
                                new ControladorPresentacioPostOpen(PostActivity.this, getApplicationContext());
                                new ControladorPresentacioLoginActivity();
                                new ControladorPresentacioLoginActivity(ControladorPresentacioLoginActivity.getActivityLogin(), getApplicationContext());
                                ControladorPresentacioPostOpen.updateAddHiddenList(post.getId()
                                        , ControladorPresentacioLoginActivity.getUserSessio());
                                Snackbar snackbar3 = Snackbar
                                        .make(coordinatorLayout, R.string.confPostHidden, Snackbar.LENGTH_LONG)
                                        .setAction(R.string.undo, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //li.getCurrentUser().removeHiddenPost(post.getId());
                                                new ControladorPresentacioLoginActivity();
                                                new ControladorPresentacioLoginActivity(ControladorPresentacioLoginActivity.getActivityLogin(), getApplicationContext());
                                                new ControladorPresentacioPostOpen(PostActivity.this, getApplicationContext());
                                                ControladorPresentacioPostOpen.updateRemoveHiddenList(post.getId()
                                                        , ControladorPresentacioLoginActivity.getUserSessio());
                                                Snackbar snackbar4 = Snackbar.make(coordinatorLayout, "Message is showed!", Snackbar.LENGTH_SHORT);
                                                snackbar4.show();
                                            }
                                        });
                                snackbar3.show();
                                break;

                            case R.id.borrar_post:
                                onDelete();

                                break;

                            case R.id.editar_post:

                                break;

                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method

        //Intent intent = getIntent();
       // String image_name = intent.getStringExtra("bitmap_img");

        this.post = (Post) Objects.requireNonNull(getIntent().getExtras()).getSerializable("post");
        TextView post_titol = findViewById(R.id.post_titol);
        post_titol.setText(post.getTitol());
        post_id = post.getId();
       // post_direccio = findViewById(R.id.post_direccio);
        post_direccio.setText(post.getLocalitzacio());
        TextView post_data = findViewById(R.id.post_data);
        post_data.setText(post.getDataIni());
        TextView post_text = findViewById(R.id.post_text);
        post_text.setText(post.getDescripcio());
        //iv.setImageBitmap(post.getImatge());

        votantsTotals = findViewById(R.id.votantsTotals);
        avgScore = findViewById(R.id.avgPunt);
        scoreBar = findViewById(R.id.ratingBar1);

        cp.getPostRating(this.post.getId());
        RatingBar ratingBar = findViewById(R.id.ratingBar2);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBar.setIsIndicator(true);
                cp.votePost(post.getId(), String.valueOf(ratingBar.getRating()));
                /*Toast.makeText(PostActivity.this,
                        "Rating changed, current rating "+ ratingBar.getRating(),
                        Toast.LENGTH_SHORT).show();*/
            }
        });

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_post_back:
               this.finish();
                break;

            case R.id.join:
                cp.joinActivity();

                break;
            case R.id.post_direccio:
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("lat", post.getLat());
                intent.putExtra("lng", post.getLng());
                startActivity(intent);
                break;
            case R.id.enviar:

                String text_comentari = ((EditText) findViewById(R.id.comentari)).getText().toString();
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                String data= year + "/" + month + "/" + day;
                Toast.makeText(
                        PostActivity.this,
                        data,
                        Toast.LENGTH_SHORT
                ).show();
                String user_id = "1";
                Comentari comment = new Comentari(user_id, text_comentari, data, post_id);
                cp.creaComentari(text_comentari, data, post_id);
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

    private void onDelete () {
        new ControladorPresentacioPostOpen(this, getApplicationContext());
        ControladorPresentacioPostOpen.deletePost(post.getId());
        this.finish();
    }

    @SuppressLint("SetTextI18n")
    public void updateRating(String puntuacio, String nombreVots) {

        //RESULTATS DE LA CRIDA A BD PER SABER LA PUNTUACIO DEL POST I EL NOMBRE DE VOTS
        Double puntuacioRounded = Math.round(Double.parseDouble(puntuacio) * 100.0) / 100.0;
        votantsTotals.setText("("+nombreVots+")");
        avgScore.setText(String.valueOf(puntuacioRounded));
        scoreBar.setRating(Float.parseFloat(puntuacio));

        Log.d("PUNTS ", puntuacio);
        Log.d("VOTS ", nombreVots);
    }
}
