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
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import integra.pesintegra.Controllers.ControladorDomini;
import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Controllers.ControladorPresentacioLoginActivity;
import integra.pesintegra.Controllers.ControladorPresentacioPostOpen;
import integra.pesintegra.Controllers.ControladorPresentacioProfileActivity;
import integra.pesintegra.Logic.Adapter.CommentListAdapter;
import integra.pesintegra.Logic.Adapter.ListAdapter;
import integra.pesintegra.Logic.Clases.Comentari;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.R;

public class PostActivity extends Activity implements View.OnClickListener{
    private static RecyclerView recyclerView;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static CommentListAdapter commentlistAdapter;
    String post_id;
    private Post post;
    private static final int SELECTED_PICTURE = 1;
    ImageView iv;
    private CoordinatorLayout coordinatorLayout;
    private ControladorPresentacioPostOpen cp;
    String current_user;
    String post_user;
    private Post_Activitat pa;
    private int places;
    TextView votantsTotals;
    TextView avgScore;
    RatingBar scoreBar;
    Boolean current;
    Boolean hidden;

    private final LoginActivity li = new LoginActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cp = new ControladorPresentacioPostOpen(this, getApplicationContext());
        setContentView(R.layout.activity_post);
        Button btn_back = findViewById(R.id.btn_post_back);
        btn_back.setOnClickListener(this);
        iv = findViewById(R.id.imatge);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        final Button tres_punts = findViewById(R.id.tres_punts);
        TextView post_direccio = findViewById(R.id.post_direccio);
        post_direccio.setOnClickListener(this);
        Button btn_enviar = findViewById(R.id.enviar);
        btn_enviar.setOnClickListener(this);

        /*PROVA DE FER ELS COMENTARIS AMB RECYCLER VIEW I AQUESTES SIDES*/

        recyclerView =  findViewById(R.id.recycler);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        List<Comentari> myDataset;
       // mAdapter = new CommentAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        /*PROVA DE FER ELS COMENTARIS AMB RECYCLER VIEW I AQUESTES SIDES*/


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

        current_user = cp.getCurrentUser();
        post_user = post.getOwner();
        current = current_user.equals(post_user);
        hidden = false;
        cp.isHidden(post_id);

        tres_punts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(PostActivity.this, tres_punts);
                Menu popupMenu = popup.getMenu();
                popup.getMenuInflater().inflate(R.menu.popup_menu, popupMenu);

                if(current){
                    //popupMenu.findItem(R.id.hide_post).setVisible(false);
                    //popupMenu.findItem(R.id.show_post).setVisible(false);
                    if(hidden) popupMenu.findItem(R.id.hide_post).setVisible(false);
                    else popupMenu.findItem(R.id.show_post).setVisible(false);
                }
                else{
                    if(hidden) popupMenu.findItem(R.id.hide_post).setVisible(false);
                    else popupMenu.findItem(R.id.show_post).setVisible(false);
                    popupMenu.findItem(R.id.editar_post).setVisible(false);
                    popupMenu.findItem(R.id.borrar_post).setVisible(false);
                }

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

                            case R.id.maps:
                                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                                intent.putExtra("lat", post.getLat());
                                intent.putExtra("lng", post.getLng());
                                startActivity(intent);
                                break;

                            case R.id.user_profile:
                                Intent perfil = new Intent(getApplicationContext(), ProfileActivity.class);
                                perfil.putExtra("profile_user", post_user);
                                startActivity(perfil);
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

        this.post = (Post) getIntent().getExtras().getSerializable("post");
        if (post.getTipus() == 'A') {
            FloatingActionButton join = findViewById(R.id.join);
            join.setVisibility(View.VISIBLE);
            join.setOnClickListener(this);
            //TODO: CRIDA PQ TORNI DE BD NMAX I NACT
            this.pa = new Post_Activitat(post);
            TextView free_places = findViewById(R.id.free_places);
            free_places.setVisibility(View.VISIBLE);
            this.places = pa.getN_max() - pa.getN_act();
            if (places > 0) {
                free_places.setText(getString(R.string.freePlaces) + places);
                free_places.setTextColor(Color.GREEN);
            }
            else {
                free_places.setTextColor(Color.RED);
                free_places.setText(R.string.noFreePlaces);

            }
        }


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

                if (places > 0) {
                    //try{
                        cp.joinActivity(pa);
                    /*}
                    catch (Exception e){

                    }*/
                }
                else {
                    Toast.makeText(
                            PostActivity.this,
                            R.string.noFreePlaces,
                            Toast.LENGTH_SHORT
                    ).show();
                }
                break;
            case R.id.post_direccio:
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("lat", post.getLat());
                intent.putExtra("lng", post.getLng());
                startActivity(intent);
                break;
            case R.id.enviar: //comentari

                String text_comentari = ((EditText) findViewById(R.id.comentari)).getText().toString();
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                String data= year + "/" + month + "/" + day;
                cp.creaComentari(text_comentari, data, post_id);
                EditText editText_comentari = (EditText) findViewById(R.id.comentari);
                editText_comentari.setText("");
                Context cc = getApplicationContext();
                Comentari comment_provisional = new Comentari("1", text_comentari, data, post_id);
                ArrayList<Comentari> comentaris_provisionals = new ArrayList<Comentari>();
                comentaris_provisionals.add(comment_provisional);
                updateFeed(comentaris_provisionals, cc);
                //update_comments();
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

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public static void updateFeed(ArrayList<Comentari> body, Context ctx) {
        commentlistAdapter = new CommentListAdapter(body);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(commentlistAdapter);
    }
}
