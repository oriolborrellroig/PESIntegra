package integra.pesintegra.Presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import integra.pesintegra.R;

public class InformationActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        Button btn_back = (Button)findViewById(R.id.btn_post_back);
        btn_back.setOnClickListener(this);

        /* adding TextView programmatically
        String[] textArray = {"One", "Two", "Three", "Four"};
        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for( int i = 0; i < textArray.length; i++ )
        {
            TextView textView = new TextView(this);
            textView.setText(textArray[i]);
            linearLayout.addView(textView);
        }
        */

        TextView emergencies = (TextView)findViewById(R.id.emergencies_txt);
        emergencies.setText("For emergencies call 1110");

        TextView hospitals = (TextView)findViewById(R.id.hospitals_txt);
        hospitals.setText("Many of Berlin's hospitals have 24-hour emergency wards, the most central being:");


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
