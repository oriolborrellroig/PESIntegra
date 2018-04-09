package integra.pesintegra.Presentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Adapter.ListAdapter;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.R;

public class AllPostsActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);


        List<Post> list_posts = new ArrayList<>();
        for (int i = 0; i < 5; ++i){
            Post p = new Post_Feina();
            p.setTitol("Empezamos");
            p.setTDataIni("12/02/2018");
            list_posts.add(p);
        }
        listAdapter = new ListAdapter(list_posts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.nav_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fabAdd :
                Intent intent = new Intent(getApplicationContext(),CreatePostActivity.class);
                startActivity(intent);
                break;
        }
    }
}
