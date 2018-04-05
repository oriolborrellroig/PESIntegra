package integra.pesintegra.Presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import integra.pesintegra.R;

public class InformationActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        Button btn_back = (Button)findViewById(R.id.btn_post_back);
        btn_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_post_back:
                this.finish();
                break;
        }

    }
}
