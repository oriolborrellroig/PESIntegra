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
import android.view.WindowManager;
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

    private RecyclerView.Adapter mAdapter;
    String post_id;
    private Post post;
    private static final int SELECTED_PICTURE = 1;
    ImageView iv;
    private CoordinatorLayout coordinatorLayout;
    private ControladorPresentacioPostOpen cp;
    String current_user;
    String post_user;
    private int places;
    private Post_Activitat pa;
    TextView votantsTotals;
    TextView avgScore;
    RatingBar scoreBar;
    RatingBar userRatingBar;
    Boolean current;
    Boolean hidden;
    Boolean assisteix;
    Boolean reported;
    private TextView free_places;
    private ArrayList<Comentari> comentaris;
    private FloatingActionButton join, disengage;

    private final LoginActivity li = new LoginActivity();
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cp = new ControladorPresentacioPostOpen(this, getApplicationContext());
        setContentView(R.layout.activity_post);
        Button btn_back = findViewById(R.id.btn_post_back);
        btn_back.setOnClickListener(this);
        iv = findViewById(R.id.imatge);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        final Button tres_punts = findViewById(R.id.tres_punts);
        TextView post_direccio = findViewById(R.id.post_direccio);
        post_direccio.setOnClickListener(this);
        Button btn_enviar = findViewById(R.id.enviar);
        btn_enviar.setOnClickListener(this);


        /*PROVA DE FER ELS COMENTARIS AMB RECYCLER VIEW I AQUESTES SIDES*/

        recyclerView =  findViewById(R.id.recycler);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
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
        comentaris = post.getComments();
        Context cc = getApplicationContext();
        updateFeed(comentaris, cc, this, cp);
        current_user = cp.getCurrentUser();
        post_user = post.getOwner();
        current = current_user.equals(post_user);
        hidden = false;
        cp.isHidden(post_id);
        //cp.getImage(post_id);
        cp.isReported(current_user, post_id);


        tres_punts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(PostActivity.this, tres_punts);
                Menu popupMenu = popup.getMenu();
                popup.getMenuInflater().inflate(R.menu.popup_menu, popupMenu);

                if(current){
                    popupMenu.findItem(R.id.report_post).setVisible(false);
                    if(hidden) popupMenu.findItem(R.id.hide_post).setVisible(false);
                    else popupMenu.findItem(R.id.show_post).setVisible(false);
                }
                else{
                    if(reported) popupMenu.findItem(R.id.report_post).setVisible(false);
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

                            case R.id.report_post:
                                cp.report_post(current_user, post_id);

                            case R.id.borrar_post:
                                onDelete();

                                break;

                            case R.id.editar_post:
                                Intent intent1 = new Intent(getApplicationContext(), EditActivityActivity.class);
                                intent1.putExtra("postId", post_id);
                                intent1.putExtra("post", post);
                                startActivity(intent1);
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
        if (post instanceof Post_Activitat) {
            this.pa = (Post_Activitat) post;
            this.join = findViewById(R.id.join);
            this.disengage = findViewById(R.id.disengage);
            join.setOnClickListener(this);
            disengage.setOnClickListener(this);
            cp.userAssisteix(post.getId(), current_user);
            this.free_places = findViewById(R.id.free_places);
            free_places.setVisibility(View.VISIBLE);
            print_free_places();
        }

        cp.getUserRating(post.getId(), current_user);
        votantsTotals = findViewById(R.id.votantsTotals);
        avgScore = findViewById(R.id.avgPunt);
        scoreBar = findViewById(R.id.ratingBar1);

        cp.getPostRating(this.post.getId());
        userRatingBar = findViewById(R.id.ratingBar2);

        userRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                cp.votePost(post.getId(), String.valueOf(ratingBar.getRating()));
            }
        });

        cp.getUserRating(this.post.getId(), this.current_user);

        //DANI T HE COMENTAT TOT AIXO
/*
        Bitmap img_pre;
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        img_pre = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        iv.setImageBitmap(img_pre);
*/
        // JAJA FINS AQUI




        //if (la imatge del post no és nul·la){
           // iv.setImageBitmap(Bitmap.createScaledBitmap(bitmap_del_post, iv.getMaxWidth(), iv.getMaxHeight(), false));
        //}



    }

    public void print_free_places() {
        this.places = pa.getAssistentsMax() - pa.getN_assistens();
        if (places > 0) {
            free_places.setText(getString(R.string.freePlaces) + places);
            free_places.setTextColor(Color.GREEN);
        }
        else {
            free_places.setTextColor(Color.RED);
            free_places.setText(R.string.noFreePlaces);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_post_back:
               this.finish();
                break;

            case R.id.disengage:
                cp.removeAttendant(post.getId(),current_user);
                cp.userAssisteix(post.getId(), current_user);
                print_free_places();
                break;

            case R.id.join:
                if (places > 0) {
                    Log.i("ERRORRR", Integer.toString(places));
                    cp.addAttendant(post.getId(), current_user);
                    cp.userAssisteix(post.getId(), current_user);
                    print_free_places();
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
                Comentari new_c = cp.creaComentari(text_comentari, data, post_id);
                comentaris.add(new_c);
                EditText editText_comentari = (EditText) findViewById(R.id.comentari);
                editText_comentari.setText("");
                Context cc = getApplicationContext();
                updateFeed(comentaris, cc, this, cp);
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
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
        Log.d("aaaa", Boolean.toString(this.hidden));
    }

    public static void updateFeed(ArrayList<Comentari> body, Context ctx, PostActivity pa, ControladorPresentacioPostOpen cpp) {
        CommentListAdapter commentlistAdapter = new CommentListAdapter(body, pa, cpp);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(commentlistAdapter);
    }

    public void setUserRating(String body) {
        Float puntuacio = Float.parseFloat(body);
        userRatingBar.setRating(puntuacio);
    }

    public void setAssistents (Integer assistents) {
        if (assistents == -1) {
            pa.setN_assistens(pa.getAssistentsMax());
        }
        else {
            pa.setN_assistens(assistents);
        }
    }

    public void userAssisteix (String assisteix) {
        if (assisteix.equals("true")) {
            this.assisteix = true;
            disengage.setVisibility(View.VISIBLE);
            join.setVisibility(View.GONE);
        }
        else {
            this.assisteix = false;
            join.setVisibility(View.VISIBLE);
            disengage.setVisibility(View.GONE);
        }
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public void loadImage(Bitmap bitmapImage) {
        iv.setImageBitmap(bitmapImage);
    }
}
