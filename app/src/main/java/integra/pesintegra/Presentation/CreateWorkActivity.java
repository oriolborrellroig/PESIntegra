package integra.pesintegra.Presentation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import integra.pesintegra.R;

public class CreateWorkActivity extends AppCompatActivity implements View.OnClickListener {

    TextView limitDate;
    TextView workHour;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_feina);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        addDatePickerListener();
        addTimePickerListener();
    }

    private void addTimePickerListener() {
        workHour = (TextView) findViewById(R.id.hourInputWor);
        workHour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateWorkActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String showHour = "";
                        String showMinute = "";
                        if (selectedHour < 10) showHour = "0" + selectedHour;
                        else showHour = ((Integer) selectedHour).toString();
                        if (selectedMinute < 10) showMinute = "0" + selectedMinute;
                        else showMinute = ((Integer) selectedMinute).toString();

                        workHour.setText( showHour + ":" + showMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }

    private void addDatePickerListener() {
        limitDate = (TextView) findViewById(R.id.dateInputWor);

        limitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateWorkActivity.this,
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
            case R.id.submitPostWor:
                String data = ((TextView) findViewById(R.id.dateInputWor)).getText().toString();
                String lloc = ((EditText) findViewById(R.id.locationInputWor)).getText().toString();
                String titol = ((EditText) findViewById(R.id.titolInputWor)).getText().toString();
                String descripcio = ((EditText) findViewById(R.id.descriptionTitolWor)).getText().toString();
                String hour = ((TextView) findViewById(R.id.hourInputWor)).getText().toString();


                //Post_Activitat activitat = new Post_Activitat();
                // PostService = ServiceManager.getPostService();
                break;
        }


    }


}
