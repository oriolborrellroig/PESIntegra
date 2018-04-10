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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Adapter.ListAdapter;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.R;

public class AllPostsActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private boolean fabIsOpen = false;

    private FloatingActionButton fabAdd,fabAddHab,fabAddFei, fabAddAct;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        //-------------connectar amb la BD!------------------
        List<Post> list_posts = new ArrayList<>();
        for (int i = 0; i < 5; ++i){
            Post p = new Post_Feina();
            p.setTitol("Empezamos");
            p.setTDataIni("12/02/2018");
            list_posts.add(p);
        }
        //-------------....................------------------

        listAdapter = new ListAdapter(list_posts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);

        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAddHab = (FloatingActionButton) findViewById(R.id.fabAddHab);
        fabAddFei = (FloatingActionButton) findViewById(R.id.fabAddFei);
        fabAddAct = (FloatingActionButton) findViewById(R.id.fabAddAct);
        fabAdd.setOnClickListener(this);
        fabAddHab.setOnClickListener(this);
        fabAddFei.setOnClickListener(this);
        fabAddAct.setOnClickListener(this);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);

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
        Intent intent;
        switch(v.getId()) {
            case R.id.fabAdd :

                if (fabIsOpen) {
                    fabAdd.startAnimation(rotate_backward);
                    fabAddHab.startAnimation(fab_close);
                    fabAddFei.startAnimation(fab_close);
                    fabAddAct.startAnimation(fab_close);

                    fabAddHab.setClickable(false);
                    fabAddFei.setClickable(false);
                    fabAddAct.setClickable(false);

                    fabIsOpen = false;
                }
                else {
                    fabAdd.startAnimation(rotate_forward);
                    fabAddHab.startAnimation(fab_open);
                    fabAddFei.startAnimation(fab_open);
                    fabAddAct.startAnimation(fab_open);

                    fabAddHab.setClickable(true);
                    fabAddFei.setClickable(true);
                    fabAddAct.setClickable(true);

                    fabIsOpen = true;

                }
                break;
            case R.id.fabAddAct :
                fabAdd.startAnimation(rotate_backward);
                fabAddHab.startAnimation(fab_close);
                fabAddFei.startAnimation(fab_close);
                fabAddAct.startAnimation(fab_close);

                fabAddHab.setClickable(false);
                fabAddFei.setClickable(false);
                fabAddAct.setClickable(false);

                fabIsOpen = false;
                intent = new Intent(getApplicationContext(),CreateActivityActivity.class);
                startActivity(intent);
                break;
            case R.id.fabAddHab :
                fabAdd.startAnimation(rotate_backward);
                fabAddHab.startAnimation(fab_close);
                fabAddFei.startAnimation(fab_close);
                fabAddAct.startAnimation(fab_close);

                fabAddHab.setClickable(false);
                fabAddFei.setClickable(false);
                fabAddAct.setClickable(false);

                fabIsOpen = false;
                intent = new Intent(getApplicationContext(),CreateActivityActivity.class);
                startActivity(intent);
                break;
            case R.id.fabAddFei :
                fabAdd.startAnimation(rotate_backward);
                fabAddHab.startAnimation(fab_close);
                fabAddFei.startAnimation(fab_close);
                fabAddAct.startAnimation(fab_close);

                fabAddHab.setClickable(false);
                fabAddFei.setClickable(false);
                fabAddAct.setClickable(false);

                fabIsOpen = false;
                intent = new Intent(getApplicationContext(),CreateActivityActivity.class);
                startActivity(intent);
                break;

        }

    }
}
