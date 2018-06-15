package integra.pesintegra.Logic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Rating;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.w3c.dom.Comment;

import java.util.Calendar;
import java.util.List;

import integra.pesintegra.Controllers.ControladorPresentacioLoginActivity;
import integra.pesintegra.Controllers.ControladorPresentacioPostOpen;
import integra.pesintegra.Logic.Clases.Comentari;
import integra.pesintegra.Logic.Clases.CommentReply;
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
    private static List<CommentReply> comments;
    private ControladorPresentacioPostOpen cp;
    private PostActivity pa;
    private ControladorPresentacioPostOpen cpp;
    private Boolean mod_or_op;

    public CommentListAdapter (List<CommentReply> list_comments, PostActivity pa, ControladorPresentacioPostOpen cpp, Boolean mod_or_op){
        comments = list_comments;
        this.pa = pa;
        this.cpp = cpp;
        this.mod_or_op = mod_or_op;
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
        CommentReply com = getItem(position);
        if (!mod_or_op || com.hasReply()){ //cas de OP o que te resposta -> no es pot contestar
            holder.send_reply_row.setVisibility(View.GONE);
        }
        holder.text.setText(com.getText());
        holder.dia.setText(String.valueOf(com.getData()));
        holder.id_comment = com.getId();
        holder.id_post = com.getPost_id();
        String id = com.getUser_id();
        //TODO: afegir la crida cp.fesimatge
        cpp.afegir_imatge(id, holder.foto_perfil, this);
        //holder.foto_perfil.setImageBitmap();
        if (com.hasReply()){
            String id_reply = com.getReply().getUser_id();
            holder.text_reply.setText(com.getReply().getText());
            holder.dia_reply.setText(String.valueOf(com.getReply().getData()));
            holder.id_comment_reply = com.getReply().getId();
            holder.c_reply = com.getReply();
            cpp.afegir_imatge(id_reply, holder.foto_perfil_reply, this);

        }else{ //cas que no te resposta -> no es veu la resposta
            holder.reply_row.setVisibility(View.GONE);
        }
        holder.c = com; //aixo guarda el comentari al holder, per quan es clica i tal
    }

    public void posa_imatge(ImageView ivv, Bitmap bitmapImage){
        ivv.setImageBitmap(bitmapImage);
    }





    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text, dia, text_reply, dia_reply; //aixo hauria de ser privat i aquestes coses pero weno
        CommentReply c, c_reply;
        public TableRow reply_row, send_reply_row;
        String id_comment, id_comment_reply;
        Button btn_enviar_r;
        EditText comment_text;
        ImageView foto_perfil, foto_perfil_reply;

        String id_post;
        View boto_menu;
        private final Context context2;




        public MyViewHolder(View view) {
            super(view);
            send_reply_row = view.findViewById(R.id.send_reply_row);
            reply_row = view.findViewById(R.id.reply_row);
            text = view.findViewById(R.id.cv_text);
            dia = view.findViewById(R.id.cv_data);
            text_reply = view.findViewById(R.id.cv_text_reply);
            dia_reply = view.findViewById(R.id.cv_data_reply);
            comment_text = view.findViewById(R.id.comentari_r);
            btn_enviar_r = view.findViewById(R.id.enviar_r);
            btn_enviar_r.setOnClickListener(this);
            foto_perfil  = view.findViewById(R.id.icon);
            foto_perfil_reply = view.findViewById(R.id.icon_reply);
            context2 = view.getContext();
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            cp = new ControladorPresentacioPostOpen(pa, context2);
            //TODO: peta al fer el get del text del comentari, pero si es fa en el default no peta, jo no entenc res
            switch (v.getId()) {
                case R.id.enviar_r:
                    String text_comentari2 = comment_text.getText().toString();
                    if (!text_comentari2.equals("")){
                        final Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        String data= year + "/" + month + "/" + day;
                        Comentari new_c = cp.creaReply(text_comentari2, data, id_post, id_comment); //potser peta lo del id_post i id_comment?
                        cp.afegeix_comentari(new_c);
                        cp.actualitzaComments();
                        //TODO: update feed

                    }
                    break;

            }




                    v.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            //Creating the instance of PopupMenu
                            PopupMenu popup = new PopupMenu(context, v);
                            Menu popupMenu = popup.getMenu();
                            popup.getMenuInflater().inflate(R.menu.popup_menu_comentari, popupMenu);

                            if(!(c.getUser_id().equals(cpp.getCurrentUser()))){
                                popupMenu.findItem(R.id.borrar_comentari).setVisible(false);
                                popupMenu.findItem(R.id.reportar_comentari).setVisible(true);
                            }else{
                                popupMenu.findItem(R.id.borrar_comentari).setVisible(true);
                                popupMenu.findItem(R.id.reportar_comentari).setVisible(false);
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
                                            for (CommentReply reply : comments){
                                                Log.d("soc", "soc a borrar un comentari");
                                                if (reply.getId().equals(id_comment)){
                                                    Log.d("sa","soc on ha de borrar la reply");
                                                    //TODO: s'han de borar les replies un cop es borra un comentari
                                                    if (reply.hasReply()) {
                                                        cp.deleteComment(id_post, reply.getReply().getId());
                                                    }
                                                }
                                            }
                                            cp.deleteComment(id_post, id_comment);
                                            cp.borra_comments_post(id_comment);
                                            cp.actualitzaComments();
                                            break;

                                        case R.id.reportar_comentari:
                                            cp.reportComment(id_post, id_comment, cpp.getCurrentUser());
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




    public CommentReply getItem(int position){
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