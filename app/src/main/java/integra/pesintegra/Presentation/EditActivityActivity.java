package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Controllers.ControladorPresentacioCreateActivity;
import integra.pesintegra.Controllers.ControladorPresentacioEditActivity;
import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.R;

public class EditActivityActivity extends AppCompatActivity implements View.OnClickListener {

    private ControladorPresentacio cntrlPresentacio;
    private ControladorPresentacioEditActivity controlador;
    private TextView limitDate;
    private TextView activityHour;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ImageView iv;
    private Spinner spinnerLang;
    private Spinner spinnerType;
    private Bitmap bitmapImage;
    private Post new_post;
    private Uri imageUri;
    private Button button;
    private Boolean clicked_esport = true;
    private Boolean clicked_musica = true;
    private Boolean clicked_cinema = true;
    private Boolean clicked_lectura = true;
    private Boolean clicked_tech = true;
    private Boolean clicked_cuina = true;
    private Boolean clicked_moda = true;
    private Boolean clicked_viatges = true;
    private Boolean clicked_art = true;
    private Context context;
    private ArrayList<String> clicked_tags;
    private String post_id;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cntrlPresentacio = new ControladorPresentacio();
        this.controlador = new ControladorPresentacioEditActivity(this, getApplicationContext());
        setContentView(R.layout.activity_create_activity);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        addDatePickerListener();
        addTimePickerListener();
        clicked_tags = new ArrayList<>();
        setSpinner();
        //setSpinnerType();

        context = getApplicationContext();
        Intent intent = getIntent();
        post_id = intent.getStringExtra("postId");
        this.post = (Post) Objects.requireNonNull(getIntent().getExtras()).getSerializable("post");
        Button enviar_btn = findViewById(R.id.submitPostAct);
        enviar_btn.setOnClickListener(this);
        FloatingActionButton add_image_btn = findViewById(R.id.add_image);
        add_image_btn.setOnClickListener(this);
        iv = findViewById(R.id.img_prev);

        TextView post_titol = findViewById(R.id.titolInputAct);
        post_titol.setText(post.getTitol());
        TextView post_text = findViewById(R.id.descriptionTitolAct);
        post_text.setText(post.getDescripcio());
        TextView post_data = findViewById(R.id.dateInputAct);
        post_data.setText(post.getDataIni());
        TextView post_hora = findViewById(R.id.hourInputAct);
        post_hora.setText(post.getHora());
        TextView post_direccio = findViewById(R.id.locationInputAct);
        post_direccio.setOnClickListener(this);
        post_direccio.setText(post.getLocalitzacio());

       ArrayList<String> interessos = (ArrayList<String>) post.getInteressos();
       Log.d("is interesos null?:", String.valueOf(interessos == null));
       /*for(String interes : interessos){
           Log.d("interes:", interes);
           Button button;
           switch (interes){
               case "esport":
                   button = findViewById(R.id.btn_esport);
                   item_seleccionat(button, "esport");
                   break;
               case "musica":
                   button = findViewById(R.id.btn_musica);
                   item_seleccionat(button, "musica");
                   break;
               case "cinema":
                   button = findViewById(R.id.btn_cinema);
                   item_seleccionat(button, "cinema");
                   break;
               case "lectura":
                   button = findViewById(R.id.btn_lectura);
                   item_seleccionat(button, "lectura");
                   break;
               case "tecnologia":
                   button = findViewById(R.id.btn_tech);
                   item_seleccionat(button, "tecnologia");
                   break;
               case "cuina":
                   button = findViewById(R.id.btn_cuina);
                   item_seleccionat(button, "cuina");
                   break;
               case "moda":
                   button = findViewById(R.id.btn_moda);
                   item_seleccionat(button, "moda");
                   break;
               case "viatges":
                   button = findViewById(R.id.btn_viatges);
                   item_seleccionat(button, "viatges");
                   break;
               case "art":
                   button = findViewById(R.id.btn_art);
                   item_seleccionat(button, "art");
                   break;
           }
       }*/
    }

    private void setSpinner() {
        spinnerLang = findViewById(R.id.languageCreatePostInputAct);

        List<String> languages = new ArrayList<>();
        languages.add(getString(R.string.catalan));
        languages.add(getString(R.string.spanish));
        languages.add(getString(R.string.english));

        spinnerLang.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, languages));
    }

    private void setSpinnerType() {
        spinnerType = findViewById(R.id.TypeEditPost);

        List<String> types = new ArrayList<>();
        types.add(getString(R.string.housing));
        types.add(getString(R.string.work));
        types.add(getString(R.string.activities));

        spinnerType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types));
    }

    private void addTimePickerListener() {
        activityHour = findViewById(R.id.hourInputAct);
        activityHour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditActivityActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String showHour = "";
                        String showMinute = "";
                        if (selectedHour < 10) showHour = "0" + selectedHour;
                        else showHour = ((Integer) selectedHour).toString();
                        if (selectedMinute < 10) showMinute = "0" + selectedMinute;
                        else showMinute = ((Integer) selectedMinute).toString();

                        activityHour.setText( showHour + ":" + showMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle(R.string.selectTime);
                mTimePicker.show();

            }
        });
    }

    private void addDatePickerListener() {
        limitDate = findViewById(R.id.dateInputAct);

        limitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditActivityActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String dia = Integer.toString(day);
                String mes = Integer.toString(month);
                if (day < 10) {
                    dia = "0" + day;
                }
                if (month < 10) {
                    mes = "0" + month;
                }
                String date = dia + "/" + mes + "/" + year;
                limitDate.setText(date);
            }
        };
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_esport:
                button = findViewById(R.id.btn_esport);
                if(clicked_esport){
                    item_seleccionat(button,"esport");
                    clicked_esport = !clicked_esport;
                }
                else {
                    item_no_seleccionat(button, "esport");
                    clicked_esport = !clicked_esport;
                }
                break;
            case R.id.btn_musica:
                button = findViewById(R.id.btn_musica);
                if(clicked_musica){
                    item_seleccionat(button, "musica");
                    clicked_musica = !clicked_musica;
                }
                else {
                    item_no_seleccionat(button, "musica");
                    clicked_musica = !clicked_musica;
                }
                break;
            case R.id.btn_cinema:
                button = findViewById(R.id.btn_cinema);
                if(clicked_cinema){
                    item_seleccionat(button, "cinema");
                    clicked_cinema = !clicked_cinema;
                }
                else {
                    item_no_seleccionat(button, "cinema");
                    clicked_cinema = !clicked_cinema;
                }
                break;
            case R.id.btn_lectura:
                button = findViewById(R.id.btn_lectura);
                if(clicked_lectura){
                    item_seleccionat(button, "lectura");
                    clicked_lectura = !clicked_lectura;
                }
                else {
                    item_no_seleccionat(button, "lectura");
                    clicked_lectura = !clicked_lectura;
                }
                break;
            case R.id.btn_tech:
                button = findViewById(R.id.btn_tech);
                if(clicked_tech){
                    item_seleccionat(button, "tecnologia");
                    clicked_tech = !clicked_tech;
                }
                else {
                    item_no_seleccionat(button, "tecnologia");
                    clicked_tech = !clicked_tech;
                }
                break;
            case R.id.btn_cuina:
                button = findViewById(R.id.btn_cuina);
                if(clicked_cuina){
                    item_seleccionat(button, "cuina");
                    clicked_cuina = !clicked_cuina;
                }
                else {
                    item_no_seleccionat(button, "cuina");
                    clicked_cuina = !clicked_cuina;
                }
                break;
            case R.id.btn_moda:
                button = findViewById(R.id.btn_moda);
                if(clicked_moda){
                    item_seleccionat(button, "moda");
                    clicked_moda = !clicked_moda;
                }
                else {
                    item_no_seleccionat(button, "moda");
                    clicked_moda = !clicked_moda;
                }
                break;
            case R.id.btn_viatges:
                button = findViewById(R.id.btn_viatges);
                if(clicked_viatges){
                    item_seleccionat(button, "viatges");
                    clicked_viatges = !clicked_viatges;
                }
                else {
                    item_no_seleccionat(button, "viatges");
                    clicked_viatges = !clicked_viatges;
                }
                break;
            case R.id.btn_art:
                button = findViewById(R.id.btn_art);
                if(clicked_art){
                    item_seleccionat(button, "art");
                    clicked_art = !clicked_art;
                }
                else {
                    item_no_seleccionat(button, "art");
                    clicked_art = !clicked_art;
                }
                break;
            case R.id.submitPostAct:
                String dataI = ((TextView) findViewById(R.id.dateInputAct)).getText().toString();
                String lloc = ((EditText) findViewById(R.id.locationInputAct)).getText().toString();
                String titol = ((EditText) findViewById(R.id.titolInputAct)).getText().toString();
                String descripcio = ((EditText) findViewById(R.id.descriptionTitolAct)).getText().toString();
                String hora = ((TextView) findViewById(R.id.hourInputAct)).getText().toString();
                String lang_spinner = spinnerLang.getSelectedItem().toString();
                String lang = "";
                if(lang_spinner.equals(getString(R.string.catalan))) lang = "CA";
                else if(lang_spinner.equals(getString(R.string.spanish))) lang = "ES";
                else lang = "EN";
                Log.d("laaaang", lang);
                LatLng coord = controlador.getLoc(lloc, context);

                //fer algo amb els booleans dels tags

                post.setTitol(titol);
                post.setTDataIni(dataI);
                post.setDescripcio(descripcio);
                post.setHora(hora);
                post.setLocalitzacio(lloc);
                post.setCoord(coord.latitude, coord.longitude);
                try {
                    controlador.editPost(this.post.getId() ,post,imageUri);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.add_image:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 52);
                break;
        }


    }

    public void item_seleccionat(Button b, String tag){
        //pintar color seleccionat primary_dark

        b.setBackgroundColor(Color.parseColor("#303F9F"));
        b.setTextColor(getResources().getColor(R.color.icons));
        clicked_tags.add(tag);
    }

    public void item_no_seleccionat(Button b, String tag){
        //pintar color no seleccionat primary_light

        b.setBackgroundColor(Color.parseColor("#C5CAE9"));
        b.setTextColor(getResources().getColor(R.color.primary_dark));
        clicked_tags.remove(tag);
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

    public String createImageFromBitmap(Bitmap bitmap) {
        String fileName = "myImage";//no .png or .jpg needed
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            FileOutputStream fo = openFileOutput(fileName, Context.MODE_PRIVATE);
            fo.write(bytes.toByteArray());
            fo.write(bytes.toByteArray());
            // remember close file output
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
            fileName = null;
        }
        return fileName;
    }

    public void showNewPost(Context ctx) {
        Intent intent_act = new Intent(ctx, PostActivity.class);

        if (bitmapImage != null) {
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byte[] byteArray = bStream.toByteArray();
        }
        //intent_act.putExtra("image", byteArray);
        intent_act.putExtra("post", post);
        this.finish();
        startActivity(intent_act);
        Log.d("location:" ,"in EditActivityActivity showNewPost!");
    }


}
