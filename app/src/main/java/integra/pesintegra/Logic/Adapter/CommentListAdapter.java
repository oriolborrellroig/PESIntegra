package integra.pesintegra.Logic.Adapter;

import android.content.Context;
import android.media.Rating;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import integra.pesintegra.Controllers.ControladorPresentacioLoginActivity;
import integra.pesintegra.Controllers.ControladorPresentacioPostOpen;
import integra.pesintegra.Logic.Clases.Comentari;
import integra.pesintegra.Logic.Clases.Post;
//import integra.pesintegra.Logic.Interface.CustomItemClickListener;
import integra.pesintegra.Presentation.EditActivityActivity;
import integra.pesintegra.Presentation.MapsActivity;
import integra.pesintegra.Presentation.PostActivity;
import integra.pesintegra.Presentation.ProfileActivity;
import integra.pesintegra.R;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder>{

    private Context context;
    private static List<Comentari> comments;
    private ControladorPresentacioPostOpen cp;
    private PostActivity pa;
    private ControladorPresentacioPostOpen cpp;

    public CommentListAdapter (List<Comentari> list_comments, PostActivity pa, ControladorPresentacioPostOpen cpp){
        this.comments = list_comments;
        this.pa = pa;
        this.cpp = cpp;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.activity_comment, parent, false);
        CardView cv = itemView.findViewById(R.id.cv);
        cv.setCardBackgroundColor(0xFFFFFFFF);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Comentari com = getItem(position);

        holder.text.setText(com.gettext());
        holder.dia.setText(String.valueOf(com.getdata()));
        holder.id_comment = com.getID();
        holder.id_post = com.getPost_id();
        holder.c = com; //aixo guarda el comentari al holder, per quan es clica i tal
    }





    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text, dia; //aixo hauria de ser privat i aquestes coses pero weno
        public Comentari c;
        String id_comment;
        String id_post;
        View boto_menu;
        private final Context context2;

        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.cv_text);
            dia = view.findViewById(R.id.cv_data);
            boto_menu = view.findViewById((R.id.opcions_comentari));
            context2 = view.getContext();
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            cp = new ControladorPresentacioPostOpen(pa, context2);





                    v.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            //Creating the instance of PopupMenu
                            PopupMenu popup = new PopupMenu(context, v);
                            Menu popupMenu = popup.getMenu();
                            popup.getMenuInflater().inflate(R.menu.popup_menu_comentari, popupMenu);

                            if(!(c.getuser_id().equals(cpp.getCurrentUser()))){
                                //popupMenu.findItem(R.id.hide_post).setVisible(false);
                                //popupMenu.findItem(R.id.show_post).setVisible(false);
                                popupMenu.findItem(R.id.borrar_comentari).setVisible(false);
                            }else{
                                popupMenu.findItem(R.id.borrar_comentari).setVisible(true);
                            }


                            //registering popup with OnMenuItemClickListener
                            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                public boolean onMenuItemClick(MenuItem item) {
                                    /*Toast.makeText(
                                            PostActivity.this,
                                            R.string.msgYouClicked + item.getTitle().toString(),
                                            Toast.LENGTH_SHORT
                                    ).show();*/

                                    switch (item.getItemId()){

                                        case R.id.borrar_comentari:
                                            cp.deleteComment(id_post, id_comment);
                                            break;

                                        case R.id.reportar_comentari:

                                            break;

                                    }
                                    return true;
                                }
                            });

                            popup.show(); //showing popup menu
                        }
                    });






            //final Intent intent = new Intent(context2, PostActivity.class);
            //intent.putExtra("post", p);
            //context2.startActivity(intent);
        }
    }




    public Comentari getItem(int position){
        return comments.get(position);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
/*
    public void removeHidden(List<String> postsH){
        if(postsH.size() > 0){
            Boolean end = false;
            int i = 0;
            while( !end){
                for(int j = 0;j < comments.size(); ++j){
                    if(comments.get(j).getId().equals(postsH.get(i))){
                        comments.remove(j);
                        j+=posts.size();
                    }
                }
                ++i;
                if(i>=postsH.size()) end = true;
            }
        }

    }*/
}