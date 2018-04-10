package integra.pesintegra.Presentation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;

import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.R;

public class CreateActivityActivity extends AppCompatActivity implements View.OnClickListener {

    ControladorPresentacio cntrlPresentacio;
    TextView limitDate;
    TextView activityHour;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cntrlPresentacio = new ControladorPresentacio();
        setContentView(R.layout.activity_create_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        addDatePickerListener();
        addDate_endPickerListener();
        addTimePickerListener();
    }

    private void addTimePickerListener() {
        activityHour = (TextView) findViewById(R.id.hourInputAct);
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
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }

    private void addDatePickerListener() {
        limitDate = (TextView) findViewById(R.id.dateInputAct);

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
                // Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                limitDate.setText(date);
            }
        };
    }

    private void addDate_endPickerListener() {
        limitDate = (TextView) findViewById(R.id.date_endInputAct);

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
                // Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
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
                String data = ((TextView) findViewById(R.id.dateInputAct)).getText().toString();
                String dataFi = ((TextView) findViewById(R.id.date_endInputAct)).getText().toString();
                String lloc = ((EditText) findViewById(R.id.locationInputAct)).getText().toString();
                String titol = ((EditText) findViewById(R.id.titolInputAct)).getText().toString();
                String descripcio = ((EditText) findViewById(R.id.descriptionTitolAct)).getText().toString();
                String hora = ((TextView) findViewById(R.id.hourInputAct)).getText().toString();


                //Post_Activitat activitat = new Post_Activitat(titol, descripcio, dataI, dataF, hora, lloc);
                //PostService = ServiceManager.getPostService();
                try {
                    cntrlPresentacio.creaPostActivitat(titol, descripcio, dataF, hora, lloc);
                } catch (Exception e) {
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Ha saltat alguna excepció dels camps");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                break;
        }


    }


}
