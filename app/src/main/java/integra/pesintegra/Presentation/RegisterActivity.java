package integra.pesintegra.Presentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import integra.pesintegra.R;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText dateEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dateEditText = (EditText)findViewById(R.id.register_datanaixement);
        dateEditText.setOnClickListener(this);

        Button cancel_btn = (Button)findViewById(R.id.registrar_cancel);
        cancel_btn.setOnClickListener(this);
        Button registrar_btn = (Button)findViewById(R.id.registrar_entrar);
        registrar_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.register_datanaixement:
                showDatePickerDialog();
                break;
            case R.id.registrar_cancel:
               // intent = new Intent(getApplicationContext(),LoginActivity.class);
                //startActivity(intent);
                this.finish();
                break;
            case R.id.registrar_entrar:
                intent = new Intent(getApplicationContext(),AllPostsActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment(dateEditText);
        newFragment.show(getFragmentManager().beginTransaction(), "datePicker");
    }
}

@SuppressLint("ValidFragment")
class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private EditText editText;

    public DatePickerFragment(EditText dateEditText) {
        editText = dateEditText;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        editText.setText(day + "/" + (month+1) +"/" + year);
    }
}
