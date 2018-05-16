package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Controllers.ControladorPresentacioChangeMailActivity;

import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.R;

public class ChangeMailActivity extends Activity implements View.OnClickListener {

    ControladorPresentacio cntrlPresentacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cntrlPresentacio = new ControladorPresentacio();
        setContentView(R.layout.activity_changemail);

        Button cancel = (Button)findViewById(R.id.change_email_cancel);
        cancel.setOnClickListener(this);
        Button register = (Button)findViewById(R.id.change_email_confirm);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.change_email_cancel:
                this.finish();
                break;
            case R.id.change_email_confirm:
                String email = ((EditText) findViewById(R.id.new_email)).getText().toString();
                String pass1 = ((EditText) findViewById(R.id.confirm_mail_pass1)).getText().toString();
                try {
                    ControladorPresentacioChangeMailActivity controlador = new ControladorPresentacioChangeMailActivity(this, getApplicationContext());
                    controlador.changeMail(email, pass1);

                    this.finish();
                    break;
                }
                catch (Exception e) {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.errorTitle)
                            .setMessage(e.getMessage())
                            .setNeutralButton(R.string.BTNback, null)
                            .show();
                }
                break;
        }
    }
}
