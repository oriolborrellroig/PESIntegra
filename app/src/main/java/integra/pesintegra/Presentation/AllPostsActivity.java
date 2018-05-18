package integra.pesintegra.Presentation;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.Controllers.ControladorPresentacioAllPostsActivity;
import integra.pesintegra.Controllers.ControladorPresentacioPostOpen;
import integra.pesintegra.Controllers.ControladorPresentacioProfileActivity;
import integra.pesintegra.Logic.Adapter.ListAdapter;
import integra.pesintegra.Logic.Clases.Post;

import integra.pesintegra.Logic.Clases.Post_Activitat;
import integra.pesintegra.Logic.Clases.Post_Feina;
import integra.pesintegra.Logic.Clases.User;

import integra.pesintegra.R;

public class AllPostsActivity extends BaseActivity implements View.OnClickListener {

    private static ListAdapter listAdapter;
    private static RecyclerView recyclerView;
    private boolean fabIsOpen = false;
    private List<Post> list_posts = new ArrayList<>();
    private static List<String> hidden_posts;
    private String postType = "none";

    private FloatingActionButton fabAdd,fabAddHab,fabAddFei, fabAddAct;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            postType = bundle.getString("type");
        }
        recyclerView =  findViewById(R.id.recycler);

        new ControladorPresentacioAllPostsActivity(AllPostsActivity.this, getApplicationContext()).loadFeedHiddenPosts();

        getPostsFromDB();

        fabAdd = findViewById(R.id.fabAdd);
        fabAddHab = findViewById(R.id.fabAddHab);
        fabAddFei = findViewById(R.id.fabAddFei);
        fabAddAct = findViewById(R.id.fabAddAct);
        fabAdd.setOnClickListener(this);
        fabAddHab.setOnClickListener(this);
        fabAddFei.setOnClickListener(this);
        fabAddAct.setOnClickListener(this);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);


    }

    private void getPostsFromDB() {
        ControladorPresentacioAllPostsActivity cs = new ControladorPresentacioAllPostsActivity(this,getApplicationContext());
        switch (postType) {
            case "any":
                cs.loadFeedAnyPosts();
                break;
            case "house":
                cs.loadFeedHousePosts();
                break;
            case "work":
                cs.loadFeedWorkPosts();
                break;
            case "activity":
                cs.loadFeedActivityPosts();
                break;
            case "propis":
                cs.loadFeedUserPropiPosts();
                break;
            case "hide":
                cs.loadFeedHiddenPosts();
                break;
            case "tags":
                cs.loadFeedTagsPosts();
                break;
            case "calendar":
                cs.loadFeedTaCalendarPosts();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.nav_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            super.onBackPressed();
        }
    }

   /* @Override
    protected void onRestart() {
        super.onRestart();

    }*/

    /*@Override
    protected void onStart() {
        super.onStart();
        getPostsFromDB();
    }*/

    @Override
    protected void onResume(){
        super.onResume();
        getPostsFromDB();
        //new ControladorPresentacioAllPostsActivity(AllPostsActivity.this, getApplicationContext()).loadFeedHiddenPosts();

        //if(listAdapter != null) listAdapter.removeHidden(hidden_posts);
        if(recyclerView != null) recyclerView.setAdapter(listAdapter);
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
                Toast.makeText(
                        AllPostsActivity.this,
                        R.string.msgClickedActivity,
                        Toast.LENGTH_SHORT
                ).show();
                intent = new Intent(getApplicationContext(),CreateActivityActivity.class);
                intent.putExtra("flag", "A");
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
                Toast.makeText(
                        AllPostsActivity.this,
                        R.string.msgClickedHousing,
                        Toast.LENGTH_SHORT
                ).show();
                intent = new Intent(getApplicationContext(),CreateActivityActivity.class);
                intent.putExtra("flag", "H");
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
                Toast.makeText(
                        AllPostsActivity.this,
                        R.string.msgClickedWork,
                        Toast.LENGTH_SHORT
                ).show();
                intent = new Intent(getApplicationContext(),CreateActivityActivity.class);
                intent.putExtra("flag", "F");
                startActivity(intent);
                break;
            case R.id.recycler :

                int position = recyclerView.getChildLayoutPosition(v);
                Log.d("Pos " , ((Integer) position).toString());
        }
    }

    public static void updateFeed(ArrayList<Post> body, Context ctx) {
        listAdapter = new ListAdapter(body);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);
    }



}
