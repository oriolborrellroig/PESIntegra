package integra.pesintegra.Presentation;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import integra.pesintegra.Controllers.ControladorPresentacioAdvancedSearchActivity;
import integra.pesintegra.Controllers.ControladorPresentacioAllPostsActivity;
import integra.pesintegra.Logic.Adapter.ListAdapter;
import integra.pesintegra.Logic.Clases.Post;

import integra.pesintegra.R;

public class AllPostsActivity extends BaseActivity implements View.OnClickListener {

    @SuppressLint("StaticFieldLeak")
    private static ListAdapter listAdapter;
    @SuppressLint("StaticFieldLeak")
    private static RecyclerView recyclerView;
    private boolean fabIsOpen = false;
    private List<Post> list_posts = new ArrayList<>();
    private static List<String> hidden_posts;
    private String postType = "none";
    private Boolean rating_clicked, creation_clicked;
    private FloatingActionButton fabAdd,fabAddHab,fabAddFei, fabAddAct, button_ratting, button_newest;
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

        new ControladorPresentacioAllPostsActivity(AllPostsActivity.this, getApplicationContext());
        ControladorPresentacioAllPostsActivity.loadFeedHiddenPosts();
        this.button_newest = findViewById(R.id.button_newest);
        button_newest.setOnClickListener(this);
        this.button_ratting = findViewById(R.id.button_ratting);
        button_ratting.setOnClickListener(this);
        rating_clicked = creation_clicked = false;
        getPostsFromDB(0);

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

    private void getPostsFromDB(Integer order) {
        ControladorPresentacioAllPostsActivity cs = new ControladorPresentacioAllPostsActivity(this,getApplicationContext());
        switch (postType) {
            case "any":
                cs.loadFeedAnyPosts(order);
                break;
            case "house":
                cs.loadFeedHousePosts(order);
                break;
            case "work":
                cs.loadFeedWorkPosts(order);
                break;
            case "activity":
                cs.loadFeedActivityPosts(order);
                break;
            case "propis":
                hide_buttons();
                ControladorPresentacioAllPostsActivity.loadFeedUserPropiPosts(getIntent().getStringExtra("user"));
                break;
            case "hide":
                hide_buttons();
                ControladorPresentacioAllPostsActivity.loadFeedHiddenPosts();
                break;
            case "tags":
                hide_buttons();
                ControladorPresentacioAllPostsActivity.loadFeedTagsPosts(order);
                break;
            case "calendar":
                hide_buttons();
                ControladorPresentacioAllPostsActivity.loadFeedTaCalendarPosts();
                break;
            case "adv_search":
                hide_buttons();
                cs.loadFeedAdvSearch(getIntent().getExtras());
                break;
            case "reported":
                ControladorPresentacioAllPostsActivity.loadReportedPosts();
        }
    }

    //0->rating unclicked clicked and click rating
    //1->vote unclicked clicked and click vote
    //2->any clicked and click same

    private void change_buttons_colour(int a) {
        if (a == 0) {
            button_ratting.setColorFilter(getResources().getColor(R.color.accent));
            button_newest.setColorFilter(getResources().getColor(R.color.primary_text));
        }
        else if (a == 1) {
            button_ratting.setColorFilter(getResources().getColor(R.color.primary_text));
            button_newest.setColorFilter(getResources().getColor(R.color.accent));
        }
        else {
            button_ratting.setColorFilter(getResources().getColor(R.color.primary_text));
            button_newest.setColorFilter(getResources().getColor(R.color.primary_text));
        }

    }

    private void hide_buttons(){
        button_ratting.setVisibility(View.GONE);
        button_newest.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        String type = getIntent().getStringExtra("type");
        DrawerLayout drawer = findViewById(R.id.nav_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            super.onBackPressed();
        }
        else if(type.equals("propis") || type.equals("hide") || type.equals("adv_search")) super.onBackPressed();
    }

    @Override
    protected void onResume(){
        super.onResume();
        getPostsFromDB(0);
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

            case R.id.button_ratting:
                if (rating_clicked) {
                    change_buttons_colour(2);
                    getPostsFromDB(0);
                    rating_clicked = false;
                }
                else { //not rating_clicked
                    if (creation_clicked) {
                        creation_clicked = false;
                    }
                    change_buttons_colour(0);
                    getPostsFromDB(2);
                    rating_clicked = true;
                }
                break;

            case R.id.button_newest:
                if (creation_clicked) {
                    change_buttons_colour(2);
                    getPostsFromDB(0);
                    creation_clicked = false;
                }
                else { //not creation_clicked
                    if (rating_clicked) {
                        rating_clicked = false;
                    }
                    change_buttons_colour(1);
                    getPostsFromDB(1);
                    creation_clicked = true;
                }
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
