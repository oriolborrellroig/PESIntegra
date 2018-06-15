package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.AlertDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import integra.pesintegra.Controllers.ControladorPresentacioChangePassword;

import integra.pesintegra.R;


public class ChangePasswordActivity extends Activity implements View.OnClickListener{

    static ControladorPresentacioChangePassword cntrlPresentacioCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cntrlPresentacioCP = new ControladorPresentacioChangePassword(this);
        setContentView(R.layout.activity_changepassword);

        Button cancel = findViewById(R.id.change_pswd_cancel);
        cancel.setOnClickListener(this);
        Button register = findViewById(R.id.change_pswd_confirm);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_pswd_cancel:
                this.finish();
                break;
            case R.id.change_pswd_confirm:
                String pass1 = ((EditText) findViewById(R.id.current_pass_in)).getText().toString();
                String pass2 = ((EditText) findViewById(R.id.confirm_pswd_pass1)).getText().toString();
                String pass3 = ((EditText) findViewById(R.id.confirm_pswd_pass2)).getText().toString();
                try {
                    cntrlPresentacioCP.changePassword(pass1, pass2, pass3);
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

    public void changedMessage(int code) {
        if (code == 200) {
            Toast.makeText(
                    ChangePasswordActivity.this,
                    R.string.passwordUpdated,
                    Toast.LENGTH_SHORT
            ).show();
            this.finish();
        }
        else {
            Toast.makeText(
                    ChangePasswordActivity.this,
                    R.string.passwordFail,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
