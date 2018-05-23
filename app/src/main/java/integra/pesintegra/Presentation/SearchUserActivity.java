package integra.pesintegra.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import integra.pesintegra.Controllers.ControladorPresentacioSearchUserActivity;
import integra.pesintegra.R;

public class SearchUserActivity extends Activity implements View.OnClickListener{
    private ControladorPresentacioSearchUserActivity cp;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cp = new ControladorPresentacioSearchUserActivity(this);
        setContentView(R.layout.activity_search_user);

        Button cancel = findViewById(R.id.search_user_cancel);
        cancel.setOnClickListener(this);
        Button register = findViewById(R.id.search_user_confirm);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_user_cancel:
                this.finish();
                break;
            case R.id.search_user_confirm:
                String user = ((EditText) findViewById(R.id.search_user)).getText().toString();
                try {
                    cp.getSearchUser(user);
                    Intent intent;
                    intent = new Intent(getApplicationContext(),ProfileActivity.class);
                    intent.putExtra("profile_user", id);
                    startActivity(intent);
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

    public void setId(String id) {
        this.id = id;
    }
}
