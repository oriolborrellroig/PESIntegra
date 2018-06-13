package integra.pesintegra.Presentation;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import integra.pesintegra.Controllers.ControladorPresentacioAllPostsActivity;
import integra.pesintegra.R;

public class CommentsRActvity extends BaseActivity implements View.OnClickListener {

    private static RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comments);

        recyclerView =  findViewById(R.id.recycler);

        getPostsFromDB();

    }

    private void getPostsFromDB() {
        //  ControladorPresentacioAllPostsActivity cs = new ControladorPresentacioAllPostsActivity(this,getApplicationContext());


    }

    @Override
    public void onClick(View v) {

    }
}
