package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import integra.pesintegra.Controllers.ControladorPresentacioLoginActivity;
import integra.pesintegra.R;


public class LoginActivity extends Activity implements View.OnClickListener {

    public static Resources resources;

    ControladorPresentacioLoginActivity cp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        loadLocale();
        super.onCreate(savedInstanceState);
        resources = getResources();
        setContentView(R.layout.activity_login);
        Button entrar_btn = findViewById(R.id.login_entrar);
        entrar_btn.setText(R.string.BTNlogin);
        entrar_btn.setOnClickListener(this);
        Button login_btn = findViewById(R.id.login_register);
        login_btn.setText(R.string.msgRegister);
        login_btn.setOnClickListener(this);

        this.cp = new ControladorPresentacioLoginActivity(this,getApplicationContext());


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.login_entrar:
                String username = ((EditText)findViewById(R.id.input_email)).getText().toString();
                String pass = ((EditText)findViewById(R.id.input_password)).getText().toString();
                cp.checkLogin(username, pass);
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
                .setMessage(R.string.msgExit)
                .setPositiveButton(R.string.msgYes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(R.string.msgNo, null)
                .show();


    }

    public void rejectLogin(Context context) {
        ((EditText)findViewById(R.id.input_password)).setText("");
        Toast.makeText(
                LoginActivity.this,
                R.string.ERRlogin,
                Toast.LENGTH_SHORT
        ).show();

    }

    public void acceptLogin(Context context) {
        Intent intent = new Intent(getApplicationContext(),AllPostsActivity.class);
        intent.putExtra("type", "any");
        startActivity(intent);
    }

    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

}
