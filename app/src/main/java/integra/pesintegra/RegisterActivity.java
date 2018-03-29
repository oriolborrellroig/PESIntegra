package integra.pesintegra;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText dateEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dateEditText = (EditText)findViewById(R.id.register_datanaixement);
        dateEditText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_datanaixement:
                showDatePickerDialog();
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
        editText.setText(day + "/" + month +"/" + year);
    }
}
