package integra.pesintegra.Presentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Calendar;

import integra.pesintegra.Controllers.ControladorPresentacioRegisterActivity;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.R;
import integra.pesintegra.Controllers.ControladorPresentacio;


public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText dateEditText;
    private ControladorPresentacio cp = new ControladorPresentacio();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dateEditText = findViewById(R.id.register_datanaixement);
        dateEditText.setOnClickListener(this);

        Button cancel_btn = findViewById(R.id.registrar_cancel);
        cancel_btn.setOnClickListener(this);
        Button registrar_btn = findViewById(R.id.registrar_entrar);
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
                String pass1 = ((EditText) findViewById(R.id.register_pass)).getText().toString();
                String pass2 = ((EditText) findViewById(R.id.register_confirmacio_pass)).getText().toString();
                String email = ((EditText) findViewById(R.id.register_email)).getText().toString();
                String dataN = ((TextView) findViewById(R.id.register_datanaixement)).getText().toString();
                String username = ((EditText) findViewById(R.id.register_username)).getText().toString();
                try {
                    User newUser = cp.comprovar_camps(pass1, pass2, email, dataN, username);
                    String hash = cp.hash_password(pass1);
                    ControladorPresentacioRegisterActivity controlador = new ControladorPresentacioRegisterActivity(this, getApplicationContext());
                    controlador.doRegister(newUser, hash);
                    break;

                } catch (Exception e) {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.errorTitle)
                            .setMessage(e.getMessage())
                            .setNeutralButton(R.string.BTNback, null)
                            .show();
                }
                break;


        }
    }

    @SuppressLint("CommitTransaction")
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment(dateEditText);
        newFragment.show(getFragmentManager().beginTransaction(), "datePicker");
    }

    public void logIn(Context context) {
        Intent intent;
        intent = new Intent(context,AllPostsActivity.class);
        intent.putExtra("type", "any");
        startActivity(intent);
        this.finish();
    }


}


