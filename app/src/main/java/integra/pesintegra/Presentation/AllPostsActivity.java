package integra.pesintegra.Presentation;
import android.content.Context;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Controllers.ControladorPresentacioAllPostsActivity;
import integra.pesintegra.Logic.Adapter.ListAdapter;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.R;

public class AllPostsActivity extends BaseActivity implements View.OnClickListener {

    private static ListAdapter listAdapter;
    private static RecyclerView recyclerView;
    private boolean fabIsOpen = false;
    private List<Post> list_posts = new ArrayList<>();
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
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        //creant posts sense BD
        /*for(int i= 0; i < 5; i++){
            Post p = new Post_Activitat();
            p.setTDataIni("12/05/2018");
            p.setTitol("Empezamos");
            list_posts.add(p);
        }
        listAdapter = new ListAdapter(list_posts);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(listAdapter);
        */
        //getPostsFromDB();

        /*PostService service = ServiceManager.getPostService();
        Call<Post> createCall = service.getPost("2");
        createCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                list_posts.add(response.body());
                Log.d("app", response.body().getTitol());
                listAdapter = new ListAdapter(list_posts);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });*/

        /*Call<Post> createCall = service.createPost(p);
        createCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                String message = t.getMessage();
                Log.d("failure", message);

            }
        });*/

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

    private void getPostsFromDB() {
        ControladorPresentacioAllPostsActivity cs = new ControladorPresentacioAllPostsActivity(this,getApplicationContext());
        if (postType.equals("any")) {
            cs.loadFeedAnyPosts();
        }
        else if (postType.equals("house")) {
            cs.loadFeedHousePosts();
        }
        else if (postType.equals("work")) {
            cs.loadFeedWorkPosts();
        }
        else if (postType.equals("activity")){
            cs.loadFeedActivityPosts();
        }
        else if (postType.equals("hide")){
            cs.loadFeedHiddenPosts();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.nav_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            super.onBackPressed();
        }
    }

    /*@Override
    protected void onRestart() {
        super.onRestart();
        getPostsFromDB();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        getPostsFromDB();
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
                        "You Clicked : Activitat",
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
                        "You Clicked : Habitatge",
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
                        "You Clicked : Feina",
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
