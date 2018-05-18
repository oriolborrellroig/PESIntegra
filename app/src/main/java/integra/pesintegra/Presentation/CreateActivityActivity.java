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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;


import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Controllers.ControladorPresentacioCreateActivity;
import integra.pesintegra.Logic.Clases.ImageBM;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.R;
import integra.pesintegra.Services.ImageService;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivityActivity extends AppCompatActivity implements View.OnClickListener {

    ControladorPresentacio cntrlPresentacio;
    private ControladorPresentacioCreateActivity controlador;
    TextView limitDate;
    TextView activityHour;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    ImageView iv;
    Bitmap bitmapImage;
    Post new_post;
    Uri imageUri;
    CheckBox checkBox_esport;
    CheckBox checkBox_musica;
    CheckBox checkBox_cinema;
    CheckBox checkBox_lectura;
    CheckBox checkBox_tecnologia;
    CheckBox checkBox_cuina;
    CheckBox checkBox_moda;
    CheckBox checkBox_viatges;
    CheckBox checkBox_art;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cntrlPresentacio = new ControladorPresentacio();
        this.controlador = new ControladorPresentacioCreateActivity(this, getApplicationContext());
        setContentView(R.layout.activity_create_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        addDatePickerListener();
        addTimePickerListener();

        context = getApplicationContext();

        Button enviar_btn = findViewById(R.id.submitPostAct);
        enviar_btn.setOnClickListener(this);
        FloatingActionButton add_image_btn = findViewById(R.id.add_image);
        add_image_btn.setOnClickListener(this);
        iv = findViewById(R.id.img_prev);
        checkBox_esport = findViewById(R.id.tag_esport);
        checkBox_musica = findViewById(R.id.tag_musica);
        checkBox_cinema = findViewById(R.id.tag_cinema);
        checkBox_lectura = findViewById(R.id.tag_lectura);
        checkBox_tecnologia = findViewById(R.id.tag_tecnologia);
        checkBox_cuina = findViewById(R.id.tag_cuina);
        checkBox_moda = findViewById(R.id.tag_moda);
        checkBox_viatges = findViewById(R.id.tag_viatges);
        checkBox_art = findViewById(R.id.tag_art);
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
                mTimePicker = new TimePickerDialog(CreateActivityActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                        CreateActivityActivity.this,
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
            case R.id.submitPostAct:
                String dataI = ((TextView) findViewById(R.id.dateInputAct)).getText().toString();
                String dataF = dataI;
                String lloc = ((EditText) findViewById(R.id.locationInputAct)).getText().toString();
                String titol = ((EditText) findViewById(R.id.titolInputAct)).getText().toString();
                String descripcio = ((EditText) findViewById(R.id.descriptionTitolAct)).getText().toString();
                String hora = ((TextView) findViewById(R.id.hourInputAct)).getText().toString();
                LatLng coord = controlador.getLoc(lloc, context);
                Boolean tag_esport = checkBox_esport.isChecked();
                Boolean tag_musica = checkBox_musica.isChecked();
                Boolean tag_cinema = checkBox_cinema.isChecked();
                Boolean tag_lectura = checkBox_lectura.isChecked();
                Boolean tag_tecnologia = checkBox_tecnologia.isChecked();
                Boolean tag_cuina = checkBox_cuina.isChecked();
                Boolean tag_moda = checkBox_moda.isChecked();
                Boolean tag_viatges = checkBox_viatges.isChecked();
                Boolean tag_art = checkBox_art.isChecked();
                //fer algo amb els booleans dels tags
                 new_post = new Post_Activitat();

                try {
                    Intent intent = getIntent();
                    String tipus = intent.getStringExtra("flag");
                    if (tipus.equals("A")) {
                        new_post = cntrlPresentacio.creaPostActivitat(titol, descripcio, dataI, dataF, hora, lloc, coord);
                    } else if (tipus.equals("F")) {
                        new_post = cntrlPresentacio.creaPostFeina(titol, descripcio, dataI, dataF, hora, lloc, coord);
                    } else if (tipus.equals("H")) {
                        new_post = cntrlPresentacio.creaPostHabitatge(titol, descripcio, dataI, dataF, hora, lloc, coord);
                    }


                    controlador.createPost(new_post,imageUri);

                    break;

                } catch (Exception e) {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.errorTitle)
                            .setMessage(e.getMessage())
                            .setNeutralButton(R.string.BTNback, null)
                            .show();
                }

                break;
            case R.id.add_image:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 52);
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
        intent_act.putExtra("post", new_post);
        this.finish();
        startActivity(intent_act);
    }


}
