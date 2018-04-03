package integra.pesintegra.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                intent = new Intent(getApplicationContext(),AllPostsActivity.class);
                startActivity(intent);
                break;
            case R.id.login_register:
                intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onBackPressed() {
    }

}
