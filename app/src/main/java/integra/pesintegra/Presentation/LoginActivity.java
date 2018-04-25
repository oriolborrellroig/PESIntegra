package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import integra.pesintegra.Controllers.ControladorServeisLoginActivity;
import integra.pesintegra.R;


public class LoginActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button entrar_btn = (Button)findViewById(R.id.login_entrar);
        entrar_btn.setOnClickListener(this);
        Button login_btn = (Button)findViewById(R.id.login_register);
        login_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.login_entrar:
                EditText euser = (EditText)findViewById(R.id.input_email);
                EditText epass = (EditText)findViewById(R.id.input_password);
                ControladorServeisLoginActivity controlador = new ControladorServeisLoginActivity(this, getApplicationContext());
                controlador.checkLogin(euser.getText().toString(), epass.getText().toString());
                //intent = new Intent(getApplicationContext(),AllPostsActivity.class);
                //startActivity(intent);
                break;
            case R.id.login_register:
                intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Segur que vols sortir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }

    public void rejectLogin(Context context) {
        //TODO: Aquesta funcio es crida quan el login no es valid

    }

    public void acceptLogin(Context context) {
        //TODO: Aquesta funcio es crida quan el login es acceptat
        Intent intent = new Intent(getApplicationContext(),AllPostsActivity.class);
        startActivity(intent);
    }
}
