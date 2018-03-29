package integra.pesintegra;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {

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
                intent = new Intent(getApplicationContext(),BaseActivity.class);
                startActivity(intent);
                break;
            case R.id.login_register:
                intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
