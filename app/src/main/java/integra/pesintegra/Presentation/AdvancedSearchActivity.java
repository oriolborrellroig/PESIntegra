package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import integra.pesintegra.R;

class AdvancedSearchActivity extends AppCompatActivity implements View.OnClickListener {
    private DatePickerDialog.OnDateSetListener mDateIniSetListener;
    private TextView DateIni;
    private DatePickerDialog.OnDateSetListener mDateFiSetListener;
    private TextView DateFi;
    private Spinner tipus;
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
    }

    private void setSpinner(){
        tipus = findViewById(R.id.tipoPostInputAct);

        List<String> posts = new ArrayList<String>();
        posts.add("@string/allPost");
        posts.add("@string/housing");
        posts.add("@string/work");
        posts.add("@string/activities");

        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, posts);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipus.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, posts));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.adv_search_btn_art:
                button = findViewById(R.id.adv_search_btn_art);
                if(clicked_art){
                    item_seleccionat(button);
                    clicked_art = !clicked_art;
                }
                else {
                    item_no_seleccionat(button);
                    clicked_art = !clicked_art;
                }
                break;

            case R.id.adv_search_btn_cinema:
                button = findViewById(R.id.adv_search_btn_cinema);
                if(clicked_cinema){
                    item_seleccionat(button);
                    clicked_cinema = !clicked_cinema;
                }
                else {
                    item_no_seleccionat(button);
                    clicked_cinema = !clicked_cinema;
                }
                break;

            case R.id.adv_search_btn_cuina:
                button = findViewById(R.id.adv_search_btn_cuina);
                if(clicked_cuina){
                    item_seleccionat(button);
                    clicked_cuina = !clicked_cuina;
                }
                else {
                    item_no_seleccionat(button);
                    clicked_cuina = !clicked_cuina;
                }
                break;

            case R.id.adv_search_btn_esport:
                button = findViewById(R.id.adv_search_btn_esport);
                if(clicked_esport){
                    item_seleccionat(button);
                    clicked_esport = !clicked_esport;
                }
                else {
                    item_no_seleccionat(button);
                    clicked_esport = !clicked_esport;
                }
                break;

            case R.id.adv_search_btn_lectura:
                button = findViewById(R.id.adv_search_btn_lectura);
                if(clicked_lectura){
                    item_seleccionat(button);
                    clicked_lectura = !clicked_lectura;
                }
                else {
                    item_no_seleccionat(button);
                    clicked_lectura = !clicked_lectura;
                }
                break;

            case R.id.adv_search_btn_moda:
                button = findViewById(R.id.adv_search_btn_moda);
                if(clicked_moda){
                    item_seleccionat(button);
                    clicked_moda = !clicked_moda;
                }
                else {
                    item_no_seleccionat(button);
                    clicked_moda = !clicked_moda;
                }
                break;

            case R.id.adv_search_btn_musica:
                button = findViewById(R.id.adv_search_btn_musica);
                if(clicked_musica){
                    item_seleccionat(button);
                    clicked_musica = !clicked_musica;
                }
                else {
                    item_no_seleccionat(button);
                    clicked_musica = !clicked_musica;
                }
                break;

            case R.id.adv_search_btn_tech:
                button = findViewById(R.id.adv_search_btn_tech);
                if(clicked_tech){
                    item_seleccionat(button);
                    clicked_tech = !clicked_tech;
                }
                else {
                    item_no_seleccionat(button);
                    clicked_tech = !clicked_tech;
                }
                break;

            case R.id.adv_search_btn_viatges:
                button = findViewById(R.id.adv_search_btn_viatges);
                if(clicked_viatges){
                    item_seleccionat(button);
                    clicked_viatges = !clicked_viatges;
                }
                else {
                    item_no_seleccionat(button);
                    clicked_viatges = !clicked_viatges;
                }
                break;
        }
    }

    private void addDateIniPickerListener() {
        DateIni = findViewById(R.id.dateIniInputAct);

        DateIni.setOnClickListener(new View.OnClickListener() {
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
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
                String date = dia + "/" + mes + "/" + year;
                DateIni.setText(date);
                Log.d("data ini", date);
            }
        };
    }

    private void addDateFiPickerListener() {
        DateFi = findViewById(R.id.dateIniInputAct);

        DateFi.setOnClickListener(new View.OnClickListener() {
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
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
                String date = dia + "/" + mes + "/" + year;
                DateFi.setText(date);
                Log.d("data fi", date);
            }
        };
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
}
