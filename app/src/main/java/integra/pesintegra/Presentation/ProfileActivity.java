package integra.pesintegra.Presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
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
import integra.pesintegra.Logic.Clases.ImageBM;
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
    String current_user;
    String profile_user;
    Boolean current;
    Boolean isMod;
    String profileTipus;
    ImageView iv;
    Bitmap bitmapImage;
    Uri imageUri;
    ControladorPresentacioProfileActivity cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setView();

        iv = findViewById(R.id.imatge_perfil);

        cp = new ControladorPresentacioProfileActivity(this, getApplicationContext());

        current_user = cp.getCurrentUser();
        profile_user = getIntent().getStringExtra("profile_user");
        current = current_user.equals(profile_user);
        posarData();
    }
    private void posarData(){
        cp.isMod(current_user);
        cp.getProfileTipus(profile_user);
        cp.getImage(current_user);
        final Button tres_punts = findViewById(R.id.tres_punts);
        tres_punts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ProfileActivity.this, tres_punts);
                //Inflating the Popup using xml file
                Menu popupMenu = popup.getMenu();

                popup.getMenuInflater().inflate(R.menu.popup_menu_perfil, popupMenu);
                if(current){
                    popupMenu.findItem(R.id.perfil_propi).setVisible(false);
                    popupMenu.findItem(R.id.ban_user).setVisible(false);
                    popupMenu.findItem(R.id.convert_to_mod).setVisible(false);
                    popupMenu.findItem(R.id.unblock_user).setVisible(false);
                }
                else{
                    popupMenu.findItem(R.id.reported_posts).setVisible(false);
                    popupMenu.findItem(R.id.afegir_imatge).setVisible(false);
                    popupMenu.findItem(R.id.canviar_mail).setVisible(false);
                    popupMenu.findItem(R.id.canviar_pswd).setVisible(false);
                    popupMenu.findItem(R.id.posts_amagats).setVisible(false);

                }
                if(!isMod){
                    popupMenu.findItem(R.id.reported_posts).setVisible(false);
                    popupMenu.findItem(R.id.ban_user).setVisible(false);
                    popupMenu.findItem(R.id.convert_to_mod).setVisible(false);
                    popupMenu.findItem(R.id.reported_posts).setVisible(false);
                    popupMenu.findItem(R.id.reported_comments).setVisible(false);
                    popupMenu.findItem(R.id.unblock_user).setVisible(false);
                }
                switch (profileTipus) {
                    case "moderador":
                        popupMenu.findItem(R.id.convert_to_mod).setVisible(false);
                        popupMenu.findItem(R.id.unblock_user).setVisible(false);
                        break;
                    case "bloquejat":
                        popupMenu.findItem(R.id.ban_user).setVisible(false);
                        break;
                    case "usuari":
                        popupMenu.findItem(R.id.unblock_user).setVisible(false);
                        break;
                }
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                ProfileActivity.this,
                                R.string.msgYouClicked + item.getTitle().toString(),
                                Toast.LENGTH_SHORT
                        ).show();



                        switch (item.getItemId()){
                            case R.id.perfil_propi:
                                Intent perfil_propi = new Intent(getApplicationContext(), ProfileActivity.class);
                                perfil_propi.putExtra("profile_user", current_user);
                                startActivity(perfil_propi);
                                break;
                            case R.id.buscar_user:
                                Intent search_user = new Intent(getApplicationContext(), SearchUserActivity.class);
                                startActivity(search_user);
                                break;
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
                                postsAmagats.putExtra("user", profile_user);
                                startActivity(postsAmagats);
                                break;
                            case R.id.posts_amagats:
                                Intent inte = new Intent(getApplicationContext(), AllPostsActivity.class);
                                inte.putExtra("type", "hide");
                                startActivity(inte);
                                break;
                            case R.id.ban_user:
                                cp.banUser(profile_user);
                                posarData();
                                break;
                            case R.id.unblock_user:
                                cp.unblockUser(profile_user);
                                posarData();
                                break;
                            case R.id.convert_to_mod:
                                cp.convertToMod(profile_user);
                                posarData();
                                break;
                            case R.id.reported_posts:
                                Intent intentt = new Intent(getApplicationContext(), AllPostsActivity.class);
                                intentt.putExtra("type", "reported");
                                startActivity(intentt);
                                break;
                            case R.id.reported_comments:

                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu



            }
        });

        ControladorPresentacioProfileActivity.getUser(profile_user);
        //Bitmap imatge_perfil = cp.getImage("profile").getBitmapImage();
        //iv.setImageBitmap(imatge_perfil);
    }


    @Override
    public void onClick(View view) {

        if(current) {

            switch (view.getId()) {

                case R.id.tres_punts:
                    break;

                case R.id.btn_esport:
                    button = findViewById(R.id.btn_esport);

                    if (clicked_esport) {
                        item_seleccionat(button);
                        clicked_esport = !clicked_esport;
                        cp.setUserInterest("esport", "true", profile_user);
                    } else {
                        item_no_seleccionat(button);
                        clicked_esport = !clicked_esport;
                        cp.setUserInterest("esport", "false", profile_user);
                    }

                    break;

                case R.id.btn_musica:
                    button = findViewById(R.id.btn_musica);

                    if (clicked_musica) {
                        item_seleccionat(button);
                        clicked_musica = !clicked_musica;
                        cp.setUserInterest("musica", "true", profile_user);
                    } else {
                        item_no_seleccionat(button);
                        clicked_musica = !clicked_musica;
                        cp.setUserInterest("musica", "false", profile_user);
                    }

                    break;

                case R.id.btn_cinema:
                    button = findViewById(R.id.btn_cinema);

                    if (clicked_cinema) {
                        item_seleccionat(button);
                        clicked_cinema = !clicked_cinema;
                        cp.setUserInterest("cinema", "true", profile_user);
                    } else {
                        item_no_seleccionat(button);
                        clicked_cinema = !clicked_cinema;
                        cp.setUserInterest("cinema", "false", profile_user);
                    }

                    break;

                case R.id.btn_lectura:
                    button = findViewById(R.id.btn_lectura);

                    if (clicked_lectura) {
                        item_seleccionat(button);
                        clicked_lectura = !clicked_lectura;
                        cp.setUserInterest("lectura", "true", profile_user);
                    } else {
                        item_no_seleccionat(button);
                        clicked_lectura = !clicked_lectura;
                        cp.setUserInterest("lectura", "false", profile_user);
                    }

                    break;


                case R.id.btn_tech:
                    button = findViewById(R.id.btn_tech);

                    if (clicked_tech) {
                        item_seleccionat(button);
                        clicked_tech = !clicked_tech;
                        cp.setUserInterest("tecnologia", "true", profile_user);
                    } else {
                        item_no_seleccionat(button);
                        clicked_tech = !clicked_tech;
                        cp.setUserInterest("tecnologia", "false", profile_user);
                    }

                    break;

                case R.id.btn_cuina:
                    button = findViewById(R.id.btn_cuina);

                    if (clicked_cuina) {
                        item_seleccionat(button);
                        clicked_cuina = !clicked_cuina;
                        cp.setUserInterest("cuina", "true", profile_user);
                    } else {
                        item_no_seleccionat(button);
                        clicked_cuina = !clicked_cuina;
                        cp.setUserInterest("cuina", "false", profile_user);
                    }

                    break;

                case R.id.btn_moda:
                    button = findViewById(R.id.btn_moda);

                    if (clicked_moda) {
                        item_seleccionat(button);
                        clicked_moda = !clicked_moda;
                        cp.setUserInterest("moda", "true", profile_user);
                    } else {
                        item_no_seleccionat(button);
                        clicked_moda = !clicked_moda;
                        cp.setUserInterest("moda", "false", profile_user);
                    }

                    break;


                case R.id.btn_viatges:
                    button = findViewById(R.id.btn_viatges);

                    if (clicked_viatges) {
                        item_seleccionat(button);
                        clicked_viatges = !clicked_viatges;
                        cp.setUserInterest("viatges", "true", profile_user);
                    } else {
                        item_no_seleccionat(button);
                        clicked_viatges = !clicked_viatges;
                        cp.setUserInterest("viatges", "false", profile_user);
                    }

                    break;

                case R.id.btn_art:
                    button = findViewById(R.id.btn_art);

                    if (clicked_art) {
                        item_seleccionat(button);
                        clicked_art = !clicked_art;
                        cp.setUserInterest("art", "true", profile_user);
                    } else {
                        item_no_seleccionat(button);
                        clicked_art = !clicked_art;
                        cp.setUserInterest("art", "false", profile_user);
                    }

                    break;

                default:
                    break;
            }
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

        if(current) mail.setText(body.getMail());
        else mail.setVisibility(View.GONE);

        TextView username = findViewById(R.id.user_name);
        username.setText(body.getUsername());
        TextView data = findViewById(R.id.user_birth_date);
        data.setText(body.getData());
        label:
        for (int i = 0; i < body.getInteressos().size(); ++i) {

            switch (body.getInteressos().get(i)) {
                case "esport":
                    button = findViewById(R.id.btn_esport);
                    item_seleccionat(button);
                    break;
                case "musica":
                    button = findViewById(R.id.btn_musica);
                    item_seleccionat(button);
                    break;
                case "cinema":
                    button = findViewById(R.id.btn_cinema);
                    item_seleccionat(button);
                    break;
                case "lectura":
                    button = findViewById(R.id.btn_lectura);
                    item_seleccionat(button);
                    break;
                case "tecnologia":
                    button = findViewById(R.id.btn_tech);
                    item_seleccionat(button);
                    break;
                case "cuina":
                    button = findViewById(R.id.btn_cuina);
                    item_seleccionat(button);
                    break;
                case "moda":
                    button = findViewById(R.id.btn_moda);
                    item_seleccionat(button);
                    break;
                case "viatges":
                    button = findViewById(R.id.btn_viatges);
                    item_seleccionat(button);
                    break;
                case "art":
                    button = findViewById(R.id.btn_art);
                    item_seleccionat(button);
                    break;
                default:
                    break label;
            }
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
                        //iv.setImageBitmap(bitmapImage);
                        ImageBM image_to_store = new ImageBM(current_user, bitmapImage);
                        cp.addProfileImage(image_to_store);
                        //carregar de la BD per provar que funciona i tal
                        //Bitmap lol_xd_prova = image_to_store.getBitmapImage();
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


    }

    public void setIsMod(String isMod) {
        this.isMod = (isMod.equals("moderador"));
    }
    public void loadImage(Bitmap bitmapImage) {
        iv.setImageBitmap(bitmapImage);
        super.loadImage(bitmapImage);

    }

    public void setProfileTipus(String profileTipus) {
        this.profileTipus = profileTipus;
    }
}
