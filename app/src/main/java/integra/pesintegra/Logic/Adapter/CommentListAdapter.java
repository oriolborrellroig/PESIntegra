package integra.pesintegra.Logic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.View;

import java.util.Calendar;
import java.util.List;

import integra.pesintegra.Controllers.ControladorPresentacioPostOpen;
import integra.pesintegra.Logic.Clases.Comentari;
import integra.pesintegra.Logic.Clases.CommentReply;
import integra.pesintegra.Presentation.PostActivity;
import integra.pesintegra.R;


public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder> {

    private Context context;
    private static List<CommentReply> comments;
    private ControladorPresentacioPostOpen cp;
    private PostActivity pa;
    private ControladorPresentacioPostOpen cpp;
    private Boolean mod_or_op;

    public CommentListAdapter(List<CommentReply> list_comments, PostActivity pa, ControladorPresentacioPostOpen cpp, Boolean mod_or_op) {
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
        if (!mod_or_op || com.hasReply()) { //cas de OP o que te resposta -> no es pot contestar
            holder.send_reply_row.setVisibility(View.GONE);
        }
        holder.text.setText(com.getText());
        holder.dia.setText(String.valueOf(com.getData()));
        holder.id_comment = com.getId();
        holder.id_post = com.getPost_id();
        String id = com.getUser_id();
        cpp.afegir_imatge(id, holder.foto_perfil, this);
        if (com.hasReply()) {
            String id_reply = com.getReply().getUser_id();
            holder.text_reply.setText(com.getReply().getText());
            holder.dia_reply.setText(String.valueOf(com.getReply().getData()));
            holder.id_comment_reply = com.getReply().getId();
            holder.c_reply = com.getReply();
            cpp.afegir_imatge(id_reply, holder.foto_perfil_reply, this);

        } else {
            holder.reply_row.setVisibility(View.GONE);
        }
        holder.c = com;
    }

    public void posa_imatge(ImageView ivv, Bitmap bitmapImage) {
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
            foto_perfil = view.findViewById(R.id.icon);
            foto_perfil_reply = view.findViewById(R.id.icon_reply);
            context2 = view.getContext();
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cp = new ControladorPresentacioPostOpen(pa, context2);
            switch (v.getId()) {
                case R.id.enviar_r:
                    String text_comentari2 = comment_text.getText().toString();
                    if (!text_comentari2.equals("")) {
                        final Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        String data = year + "/" + month + "/" + day;
                        Comentari new_c = cp.creaReply(text_comentari2, data, id_post, id_comment); //potser peta lo del id_post i id_comment?
                        cp.afegeix_comentari(new_c);
                        cp.actualitzaComments();

                    }
                    break;

            }


            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    PopupMenu popup = new PopupMenu(context, v);
                    Menu popupMenu = popup.getMenu();
                    popup.getMenuInflater().inflate(R.menu.popup_menu_comentari, popupMenu);

                    if ((c.getUser_id().equals(cpp.getCurrentUser())) || mod_or_op) {
                        popupMenu.findItem(R.id.borrar_comentari).setVisible(true);
                        popupMenu.findItem(R.id.reportar_comentari).setVisible(false);
                    } else {
                        popupMenu.findItem(R.id.borrar_comentari).setVisible(false);
                        popupMenu.findItem(R.id.reportar_comentari).setVisible(true);
                    }


                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()) {

                                case R.id.borrar_comentari:
                                    for (CommentReply reply : comments) {
                                        if (reply.getId().equals(id_comment)) {
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

                    popup.show();
                }
            });
        }
    }


    public CommentReply getItem(int position) {
        return comments.get(position);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

}