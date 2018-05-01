package integra.pesintegra.Presentation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import integra.pesintegra.R;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    Button button;
    Boolean clicked_esport = true;
    Boolean clicked_musica = true;
    Boolean clicked_cinema = true;
    Boolean clicked_lectura = true;
    Boolean clicked_tech = true;
    Boolean clicked_cuina = true;
    Boolean clicked_moda = true;
    Boolean clicked_viatges = true;
    Boolean clicked_art = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setView();

        Button change_email = (Button)findViewById(R.id.btn_change_mail);
        change_email.setOnClickListener(this);

        /*Button button_esport = findViewById(R.id.btn_esport);
        button_esport.setBackgroundColor(Color.parseColor("#C5CAE9")); (....)*/
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_change_mail:
                Intent intent = new Intent(getApplicationContext(), ChangeMailActivity.class);
                startActivity(intent);
                break;


            case R.id.btn_esport:
                button = findViewById(R.id.btn_esport);

                if(clicked_esport){
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("Esta clicat");

                    item_seleccionat(button);
                    clicked_esport = !clicked_esport;
                }

                else {
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("no esta clicat");

                    item_no_seleccionat(button);
                    clicked_esport = !clicked_esport;
                }

                break;

            case R.id.btn_musica:
                System.out.println("has CLICKAT!----------");
                button = findViewById(R.id.btn_musica);

                if(clicked_musica){
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("Esta clicat");

                    item_seleccionat(button);
                    clicked_musica = !clicked_musica;
                }

                else {
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("no esta clicat");

                    item_no_seleccionat(button);
                    clicked_musica = !clicked_musica;
                }

                break;

            case R.id.btn_cinema:
                System.out.println("has CLICKAT!----------");
                button = findViewById(R.id.btn_cinema);

                if(clicked_cinema){
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("Esta clicat");

                    item_seleccionat(button);
                    clicked_cinema = !clicked_cinema;
                }

                else {
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("no esta clicat");

                    item_no_seleccionat(button);
                    clicked_cinema = !clicked_cinema;
                }

                break;

            case R.id.btn_lectura:
                System.out.println("has CLICKAT!----------");
                button = findViewById(R.id.btn_lectura);

                if(clicked_lectura){
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("Esta clicat");

                    item_seleccionat(button);
                    clicked_lectura = !clicked_lectura;
                }

                else {
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("no esta clicat");

                    item_no_seleccionat(button);
                    clicked_lectura = !clicked_lectura;
                }

                break;


            /*case R.id.btn_tech:
                System.out.println("has CLICKAT!----------");
                button = findViewById(R.id.btn_tech);

                if(clicked_tech){
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("Esta clicat");

                    item_seleccionat(button);
                    clicked_tech = !clicked_tech;
                }

                else {
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("no esta clicat");

                    item_no_seleccionat(button);
                    clicked_tech = !clicked_tech;
                }*/

            case R.id.btn_cuina:
                System.out.println("has CLICKAT!----------");
                button = findViewById(R.id.btn_cuina);

                if(clicked_cuina){
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("Esta clicat");

                    item_seleccionat(button);
                    clicked_cuina = !clicked_cuina;
                }

                else {
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("no esta clicat");

                    item_no_seleccionat(button);
                    clicked_cuina = !clicked_cuina;
                }

                break;

            case R.id.btn_moda:
                System.out.println("has CLICKAT!----------");
                button = findViewById(R.id.btn_moda);

                if(clicked_moda){
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("Esta clicat");

                    item_seleccionat(button);
                    clicked_moda = !clicked_moda;
                }

                else {
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("no esta clicat");

                    item_no_seleccionat(button);
                    clicked_moda = !clicked_moda;
                }

                break;


            case R.id.btn_viatges:
                System.out.println("has CLICKAT!----------");
                button = findViewById(R.id.btn_viatges);

                if(clicked_viatges){
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("Esta clicat");

                    item_seleccionat(button);
                    clicked_viatges = !clicked_viatges;
                }

                else {
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("no esta clicat");

                    item_no_seleccionat(button);
                    clicked_viatges = !clicked_viatges;
                }

                break;

            case R.id.btn_art:
                System.out.println("has CLICKAT!----------");
                button = findViewById(R.id.btn_art);

                if(clicked_art){
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("Esta clicat");

                    item_seleccionat(button);
                    clicked_art = !clicked_art;
                }

                else {
                    System.out.println("has tronar a clicar pillina!----------");
                    System.out.println("no esta clicat");

                    item_no_seleccionat(button);
                    clicked_art = !clicked_art;
                }

                break;

            default:
                break;
        }
    }

    public void item_seleccionat(Button b){
        //pintar color seleccionat primary_dark

        //b = findViewById(R.id.);
        b.setBackgroundColor(Color.parseColor("#303F9F"));
        b.setTextColor(getResources().getColor(R.color.icons));
    }

    public void item_no_seleccionat(Button b){
        //pintar color no seleccionat primary_light

        //b = findViewById(R.id.btn_esport);
        b.setBackgroundColor(Color.parseColor("#C5CAE9"));
        b.setTextColor(getResources().getColor(R.color.primary_dark));
    }
}
