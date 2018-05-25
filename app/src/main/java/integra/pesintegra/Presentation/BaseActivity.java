package integra.pesintegra.Presentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import integra.pesintegra.Controllers.ControladorPresentacio;
import integra.pesintegra.R;

public class BaseActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener {

    ControladorPresentacio cntrlPresentacio;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cntrlPresentacio = new ControladorPresentacio();
        setContentView(R.layout.activity_base);
        setView();


    }

    protected void setView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout mDrawerLayout = findViewById(R.id.nav_drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResID) {

        @SuppressLint("InflateParams") DrawerLayout fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout frameLayout = (FrameLayout) fullLayout.findViewById(R.id.frame_layout_base);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        super.setContentView(fullLayout);
        setView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.allPosts:
                intent = new Intent(getApplicationContext(), AllPostsActivity.class);
                intent.putExtra("type", "any");
                startActivity(intent);
                break;
            case R.id.work:
                intent = new Intent(getApplicationContext(), AllPostsActivity.class);
                intent.putExtra("type", "work");
                startActivity(intent);
                break;
            case R.id.house:
                intent = new Intent(getApplicationContext(), AllPostsActivity.class);
                intent.putExtra("type", "house");
                startActivity(intent);
                break;
            case R.id.activities:
                intent = new Intent(getApplicationContext(), AllPostsActivity.class);
                intent.putExtra("type", "activity");
                startActivity(intent);
                break;
            case R.id.tags:
                intent = new Intent(getApplicationContext(), AllPostsActivity.class);
                intent.putExtra("type", "tags");
                startActivity(intent);
                break;
            case R.id.adv_search:
                intent = new Intent(getApplicationContext(), AdvancedSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("profile_user", cntrlPresentacio.getSessioUser());
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(getApplicationContext(), AllPostsActivity.class);
                intent.putExtra("type", "calendar");
                startActivity(intent);
                break;
            case R.id.information:
                intent = new Intent(getApplicationContext(), InformationActivity.class);
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                new AlertDialog.Builder(this)
                        .setMessage(R.string.msgExitApp)
                        .setPositiveButton(R.string.msgYes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Intent startMain = new Intent(Intent.ACTION_MAIN);
                                startMain.addCategory(Intent.CATEGORY_HOME);
                                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(startMain);
                            }
                        })
                        .setNegativeButton(R.string.msgNo, null)
                        .show();

                break;
            case R.id.logout:
                new AlertDialog.Builder(this)
                        .setMessage(R.string.msgLogOut)
                        .setPositiveButton(R.string.msgYes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent finishApp = new Intent(BaseActivity.this, LoginActivity.class);
                                finishApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finishApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                finishApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                //cntrlPresentacio.logout();
                                startActivity(finishApp);

                            }
                        })
                        .setNegativeButton(R.string.msgNo, null)
                        .show();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.nav_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.nav_drawer);
        //drawer.closeDrawer(GravityCompat.START);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
