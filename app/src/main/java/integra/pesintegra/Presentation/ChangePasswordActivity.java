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

import integra.pesintegra.Controllers.ControladorPresentacioChangePassword;
import integra.pesintegra.Controllers.ControladorPresentacioLoginActivity;
import integra.pesintegra.Logic.Clases.User;
import integra.pesintegra.R;

public class ChangePasswordActivity extends Activity implements View.OnClickListener{
    static ControladorPresentacio cntrlPresentacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cntrlPresentacio = new ControladorPresentacio();
        setContentView(R.layout.activity_changepassword);

        Button cancel = (Button)findViewById(R.id.change_pswd_cancel);
        cancel.setOnClickListener(this);
        Button register = (Button)findViewById(R.id.change_pswd_confirm);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.change_pswd_cancel:
                this.finish();
                break;
            case R.id.change_pswd_confirm:
                String pass1 = ((EditText) findViewById(R.id.current_pass_in)).getText().toString();
                String pass2 = ((EditText) findViewById(R.id.confirm_pswd_pass1)).getText().toString();
                String pass3 = ((EditText) findViewById(R.id.confirm_pswd_pass2)).getText().toString();
                try {
                    new ControladorPresentacioLoginActivity();
                    new ControladorPresentacioLoginActivity(ControladorPresentacioLoginActivity.getActivityLogin(), getApplicationContext());
                    String userID = ControladorPresentacioLoginActivity.getUserSessio();
                    new ControladorPresentacioChangePassword(this, getApplicationContext()).changePassword(userID, pass1, pass2, pass3);
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
