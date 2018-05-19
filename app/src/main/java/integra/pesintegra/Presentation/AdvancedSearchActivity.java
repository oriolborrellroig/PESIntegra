package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import integra.pesintegra.Controllers.ControladorPresentacioAdvancedSearchActivity;
import integra.pesintegra.R;

class AdvancedSearchActivity extends AppCompatActivity implements View.OnClickListener {
    private ControladorPresentacioAdvancedSearchActivity cp;
    private DatePickerDialog.OnDateSetListener mDateIniSetListener;
    private TextView ETDateIni;
    private DatePickerDialog.OnDateSetListener mDateFiSetListener;
    private TextView ETDateFi;
    private String text_to_search;
    private String user;
    private String tipus;
    private String dateIni;
    private String dateFi;
    private Spinner ETtipus;
    private Spinner ETlanguage;
    private Button BTN_search;
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
    private List<String> clicked_tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advancedsearch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setSpinner();

        addDateIniPickerListener();
        addDateFiPickerListener();

        BTN_search = findViewById(R.id.submitSearchAct);
        BTN_search.setOnClickListener(this);

        clicked_tags = new ArrayList<>();

        Context context = getApplicationContext();

        cp = new ControladorPresentacioAdvancedSearchActivity(context);
    }

    private void setSpinner(){
        ETtipus = findViewById(R.id.tipoPostInputAct);

        List<String> posts = new ArrayList<>();
        posts.add(getString(R.string.allPost));
        posts.add(getString(R.string.housing));
        posts.add(getString(R.string.work));
        posts.add(getString(R.string.activities));

        ETtipus.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, posts));


        ETlanguage = findViewById(R.id.languageInputAct);
        List<String> lang = new ArrayList<>();
        lang.add(getString(R.string.any_lang));
        lang.add(getString(R.string.catalan));
        lang.add(getString(R.string.spanish));
        lang.add(getString(R.string.english));

        ETlanguage.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lang));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.adv_search_btn_art:
                button = findViewById(R.id.adv_search_btn_art);
                if(clicked_art){
                    item_seleccionat(button, "art");
                    clicked_art = !clicked_art;
                }
                else {
                    item_no_seleccionat(button, "art");
                    clicked_art = !clicked_art;
                }
                break;

            case R.id.adv_search_btn_cinema:
                button = findViewById(R.id.adv_search_btn_cinema);
                if(clicked_cinema){
                    item_seleccionat(button, "cinema");
                    clicked_cinema = !clicked_cinema;
                }
                else {
                    item_no_seleccionat(button, "cinema");
                    clicked_cinema = !clicked_cinema;
                }
                break;

            case R.id.adv_search_btn_cuina:
                button = findViewById(R.id.adv_search_btn_cuina);
                if(clicked_cuina){
                    item_seleccionat(button, "cuina");
                    clicked_cuina = !clicked_cuina;
                }
                else {
                    item_no_seleccionat(button, "cuina");
                    clicked_cuina = !clicked_cuina;
                }
                break;

            case R.id.adv_search_btn_esport:
                button = findViewById(R.id.adv_search_btn_esport);
                if(clicked_esport){
                    item_seleccionat(button, "esport");
                    clicked_esport = !clicked_esport;
                }
                else {
                    item_no_seleccionat(button, "esport");
                    clicked_esport = !clicked_esport;
                }
                break;

            case R.id.adv_search_btn_lectura:
                button = findViewById(R.id.adv_search_btn_lectura);
                if(clicked_lectura){
                    item_seleccionat(button, "lectura");
                    clicked_lectura = !clicked_lectura;
                }
                else {
                    item_no_seleccionat(button, "lectura");
                    clicked_lectura = !clicked_lectura;
                }
                break;

            case R.id.adv_search_btn_moda:
                button = findViewById(R.id.adv_search_btn_moda);
                if(clicked_moda){
                    item_seleccionat(button, "moda");
                    clicked_moda = !clicked_moda;
                }
                else {
                    item_no_seleccionat(button, "moda");
                    clicked_moda = !clicked_moda;
                }
                break;

            case R.id.adv_search_btn_musica:
                button = findViewById(R.id.adv_search_btn_musica);
                if(clicked_musica){
                    item_seleccionat(button, "musica");
                    clicked_musica = !clicked_musica;
                }
                else {
                    item_no_seleccionat(button, "musica");
                    clicked_musica = !clicked_musica;
                }
                break;

            case R.id.adv_search_btn_tech:
                button = findViewById(R.id.adv_search_btn_tech);
                if(clicked_tech){
                    item_seleccionat(button, "tech");
                    clicked_tech = !clicked_tech;
                }
                else {
                    item_no_seleccionat(button, "tech");
                    clicked_tech = !clicked_tech;
                }
                break;

            case R.id.adv_search_btn_viatges:
                button = findViewById(R.id.adv_search_btn_viatges);
                if(clicked_viatges){
                    item_seleccionat(button, "viatges");
                    clicked_viatges = !clicked_viatges;
                }
                else {
                    item_no_seleccionat(button, "viatges");
                    clicked_viatges = !clicked_viatges;
                }
                break;

            case R.id.submitSearchAct:
                getFields();
                try {
                    cp.comprovaCamps(dateIni, dateFi, user);

                }  catch (Exception e) {
                     new AlertDialog.Builder(this)
                    .setTitle(R.string.errorTitle)
                    .setMessage(e.getMessage())
                    .setNeutralButton(R.string.BTNback, null)
                    .show();
                }
                break;
        }
    }

    private void getFields() {
        EditText ETtext = findViewById(R.id.queryInputAct);
        user = ETtext.getText().toString();

        tipus = ETtipus.getSelectedItem().toString();

        EditText ETuser = findViewById(R.id.userInputAct);
        user = ETuser.getText().toString();
    }

    private void addDateIniPickerListener() {
        ETDateIni = findViewById(R.id.dateIniInputAct);

        ETDateIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AdvancedSearchActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateIniSetListener,
                        year,month,day);
                dialog.show();
            }
        });

        mDateIniSetListener = new DatePickerDialog.OnDateSetListener() {
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
                dateIni = dia + "/" + mes + "/" + year;
                ETDateIni.setText(dateIni);
            }
        };
    }

    private void addDateFiPickerListener() {
        ETDateFi = findViewById(R.id.dateFiInputAct);

        ETDateFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AdvancedSearchActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateFiSetListener,
                        year,month,day);
                dialog.show();
            }
        });

        mDateFiSetListener = new DatePickerDialog.OnDateSetListener() {
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
                dateFi = dia + "/" + mes + "/" + year;
                ETDateFi.setText(dateFi);
            }
        };
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
}
