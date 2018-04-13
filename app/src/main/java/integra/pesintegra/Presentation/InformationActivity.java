package integra.pesintegra.Presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import integra.pesintegra.Controllers.ControladorServeisInfoUtil;
import integra.pesintegra.R;

public class InformationActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        Button btn_back = (Button)findViewById(R.id.btn_post_back);
        btn_back.setOnClickListener(this);

        ControladorServeisInfoUtil controlador = new ControladorServeisInfoUtil(this,getApplicationContext());
        controlador.getInfo();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_post_back:
                this.finish();
                break;
        }

    }

    public void posaInformacio(JsonObject body) {
        TextView emergencies = (TextView)findViewById(R.id.emergencies_txt);
        emergencies.setText(body.get("emergencies").toString().replace("\"", ""));

        TextView hospitals = (TextView)findViewById(R.id.hospitals_txt);
        hospitals.setText(body.get("hospitals").toString().replace("\"", ""));
    }
}
