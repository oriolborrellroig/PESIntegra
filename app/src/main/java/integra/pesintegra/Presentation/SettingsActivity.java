package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import integra.pesintegra.Controllers.ControladorPresentacioSettingsActivity;
import integra.pesintegra.R;


public class SettingsActivity extends Activity implements View.OnClickListener {

    private Spinner langType;
    private ControladorPresentacioSettingsActivity cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cp = new ControladorPresentacioSettingsActivity();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setContentView(R.layout.activity_settings);
        Context context = getApplicationContext();
        setSpinner();
    }

    private void setSpinner() {
        langType = findViewById(R.id.chosenLanguage);

        List<String> languages = new ArrayList<>();
        languages.add(getString(R.string.english));
        languages.add(getString(R.string.spanish));
        languages.add(getString(R.string.catalan));

        langType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, languages));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.settingsCancel:
                this.finish();
                break;

            case R.id.settingsSave:
                if (langType.getSelectedItemPosition() == 0) {
                    setLanaguage("en");
                }
                else if (langType.getSelectedItemPosition() == 1){
                    setLanaguage("es");
                }
                else if (langType.getSelectedItemPosition() == 2){
                    setLanaguage("ca");
                }
                else {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.errorTitle)
                            .setMessage(R.string.msgBadSettings)
                            .setNeutralButton(R.string.BTNback, null)
                            .show();
                }
                this.finish();
                break;
        }
    }

    public void setLanaguage(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}
