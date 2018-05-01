package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Controllers.ControladorServeisRegisterActivity;
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
                String pass2 = ((EditText) findViewById(R.id.confirm_mail_pass2)).getText().toString();
                try {
                    cntrlPresentacio.change_email(email, pass1, pass2);
                    /*
                    ControladorServeisRegisterActivity controlador = new ControladorServeisRegisterActivity(this, getApplicationContext());
                    controlador.changeEmail();
                    intent = new Intent(getApplicationContext(),AllPostsActivity.class);
                    startActivity(intent);
                    */
                    this.finish();
                    break;
                }
                catch (Exception e) {
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage(e.getMessage())
                            .setNeutralButton("Torna", null)
                            .show();
            }
                break;
        }
    }
}
