package integra.pesintegra.Presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.util.List;

import integra.pesintegra.Controllers.ControladorPresentacioProfileActivity;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.R;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    Button button;
    Boolean clicked_esport = true;
    Boolean clicked_musica = true;
    Boolean clicked_cinema = true;
    Boolean clicked_lectura = true;
    Boolean clicked_tech = true;
    Boolean clicked_cuina = true;
    Boolean clicked_moda = true;
    Boolean clicked_viatges = true;
    Boolean clicked_art = true;
    ImageView iv;
    Bitmap bitmapImage;
    Uri imageUri;
    ControladorPresentacioProfileActivity cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setView();

/*
        Button change_profile = (Button)findViewById(R.id.btn_change_profile);
        change_profile
                .setOnClickListener(this);
*/
        iv = (ImageView) findViewById(R.id.imatge_perfil);

        cp = new ControladorPresentacioProfileActivity(this, getApplicationContext());
        //cp.setUserInterest("cinema", "true");



        final Button tres_punts = (Button) findViewById(R.id.tres_punts);
        tres_punts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ProfileActivity.this, tres_punts);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu_perfil, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                ProfileActivity.this,
                                R.string.msgYouClicked + item.getTitle().toString(),
                                Toast.LENGTH_SHORT
                        ).show();

                        switch (item.getItemId()){
                            case R.id.afegir_imatge:
                                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(i, 52);
                                break;
                            case R.id.canviar_mail:
                                Intent intent = new Intent(getApplicationContext(), ChangeMailActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.canviar_pswd:
                                Intent chng = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                                startActivity(chng);
                                break;
                            case R.id.posts_propis:
                                Intent postsAmagats = new Intent(getApplicationContext(), AllPostsActivity.class);
                                postsAmagats.putExtra("type", "propis");
                                startActivity(postsAmagats);
                                break;
                            case R.id.posts_amagats:
                                Intent inte = new Intent(getApplicationContext(), AllPostsActivity.class);
                                inte.putExtra("type", "hide");
                                startActivity(inte);
                                break;

                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });

        ControladorPresentacioProfileActivity.getUser();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tres_punts:
                break;
            case R.id.btn_esport:
                button = findViewById(R.id.btn_esport);
                if(clicked_esport){
                    item_seleccionat(button);
                    clicked_esport = !clicked_esport;
                    cp.setUserInterest("esport", "true");
                }
                else {
                    item_no_seleccionat(button);
                    clicked_esport = !clicked_esport;
                    cp.setUserInterest("esport", "false");
                }
                break;
            case R.id.btn_musica:
                button = findViewById(R.id.btn_musica);
                if(clicked_musica){
                    item_seleccionat(button);
                    clicked_musica = !clicked_musica;
                    cp.setUserInterest("musica", "true");
                }
                else {
                    item_no_seleccionat(button);
                    clicked_musica = !clicked_musica;
                    cp.setUserInterest("musica", "false");
                }
                break;
            case R.id.btn_cinema:
                button = findViewById(R.id.btn_cinema);
                if(clicked_cinema){
                    item_seleccionat(button);
                    clicked_cinema = !clicked_cinema;
                    cp.setUserInterest("cinema", "true");
                }
                else {
                    item_no_seleccionat(button);
                    clicked_cinema = !clicked_cinema;
                    cp.setUserInterest("cinema", "false");
                }
                break;
            case R.id.btn_lectura:
                button = findViewById(R.id.btn_lectura);
                if(clicked_lectura){
                    item_seleccionat(button);
                    clicked_lectura = !clicked_lectura;
                    cp.setUserInterest("lectura", "true");
                }
                else {
                    item_no_seleccionat(button);
                    clicked_lectura = !clicked_lectura;
                    cp.setUserInterest("lectura", "false");
                }
                break;
            case R.id.btn_tech:
                button = findViewById(R.id.btn_tech);
                if(clicked_tech){
                    item_seleccionat(button);
                    clicked_tech = !clicked_tech;
                    cp.setUserInterest("tecnologia", "true");
                }
                else {
                    item_no_seleccionat(button);
                    clicked_tech = !clicked_tech;
                    cp.setUserInterest("tecnologia", "false");
                }
                break;
            case R.id.btn_cuina:
               button = findViewById(R.id.btn_cuina);
                if(clicked_cuina){
                   item_seleccionat(button);
                    clicked_cuina = !clicked_cuina;
                    cp.setUserInterest("cuina", "true");
                }
                else {
                    item_no_seleccionat(button);
                    clicked_cuina = !clicked_cuina;
                    cp.setUserInterest("cuina", "false");
                }
                break;
            case R.id.btn_moda:
                button = findViewById(R.id.btn_moda);
                if(clicked_moda){
                    item_seleccionat(button);
                    clicked_moda = !clicked_moda;
                    cp.setUserInterest("moda", "true");
                }
                else {
                    item_no_seleccionat(button);
                    clicked_moda = !clicked_moda;
                    cp.setUserInterest("moda", "false");
                }
                break;
            case R.id.btn_viatges:
                button = findViewById(R.id.btn_viatges);
                if(clicked_viatges){
                    item_seleccionat(button);
                    clicked_viatges = !clicked_viatges;
                    cp.setUserInterest("viatges", "true");
                }
                else {
                    item_no_seleccionat(button);
                    clicked_viatges = !clicked_viatges;
                    cp.setUserInterest("viatges", "false");
                }
                break;
            case R.id.btn_art:
                button = findViewById(R.id.btn_art);
                if(clicked_art){
                    item_seleccionat(button);
                    clicked_art = !clicked_art;
                    cp.setUserInterest("art", "true");
                }
                else {
                    item_no_seleccionat(button);
                    clicked_art = !clicked_art;
                    cp.setUserInterest("art", "false");
                }
                break;
            default:
                break;
        }
    }

    public void item_seleccionat(Button b){
        //pintar color seleccionat primary_dark

        b.setBackgroundColor(Color.parseColor("#303F9F"));
        b.setTextColor(getResources().getColor(R.color.icons));

    }

    public void item_no_seleccionat(Button b){
        //pintar color no seleccionat primary_light

        b.setBackgroundColor(Color.parseColor("#C5CAE9"));
        b.setTextColor(getResources().getColor(R.color.primary_dark));

    }

    public void setUserInfo(User body, Context context) {
        TextView mail = findViewById(R.id.user_mail);
        mail.setText(body.getMail());
        TextView data = findViewById(R.id.user_birth_date);
        data.setText(body.getData());
        for ( int i = 0; i < body.getInteressos().size(); ++i){

            if (body.getInteressos().get(i).equals("esport") ){
                button = findViewById(R.id.btn_esport);
                item_seleccionat(button);
            }
            else if (body.getInteressos().get(i).equals("musica")){
                button = findViewById(R.id.btn_musica);
                item_seleccionat(button);
            }
            else if (body.getInteressos().get(i).equals("cinema")){
                button = findViewById(R.id.btn_cinema);
                item_seleccionat(button);
            }
            else if (body.getInteressos().get(i).equals("lectura")){
                button = findViewById(R.id.btn_lectura);
                item_seleccionat(button);
            }
            else if (body.getInteressos().get(i).equals("tecnologia")){
                button = findViewById(R.id.btn_tech);
                item_seleccionat(button);
            }
            else if (body.getInteressos().get(i).equals("cuina")){
                button = findViewById(R.id.btn_cuina);
                item_seleccionat(button);
            }
            else if (body.getInteressos().get(i).equals("moda")){
                button = findViewById(R.id.btn_moda);
                item_seleccionat(button);
            }
            else if (body.getInteressos().get(i).equals("viatges")){
                button = findViewById(R.id.btn_viatges);
                item_seleccionat(button);
            }
            else if (body.getInteressos().get(i).equals("art")){
                button = findViewById(R.id.btn_art);
                item_seleccionat(button);
            }
            else break;
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
                        bitmapImage = decodeBitmap(selectedImage, alcada, amplada);
                        iv.setImageBitmap(bitmapImage);
                        imageUri = selectedImage;
                        //guardar imatge a la bd
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // Show the Selected Image on ImageView



                }
        }
    }
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

    public void updateInterestInfo(String interes, String valor) {

        //Log.d("interes : ", interes);
        //Log.d("valorrr : ", valor);
        //fer coses amb el nom del interes canviat i el nou valor true false en format string.
    }
}
