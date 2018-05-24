package integra.pesintegra.Logic.Adapter;

import android.content.Context;
import android.media.Rating;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import com.google.gson.JsonObject;

import java.util.List;

import integra.pesintegra.Logic.Clases.Comentari;
import integra.pesintegra.Logic.Clases.Post;
//import integra.pesintegra.Logic.Interface.CustomItemClickListener;
import integra.pesintegra.Presentation.PostActivity;
import integra.pesintegra.R;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder>{

    private Context context;
    private static List<Comentari> comments;

    public CommentListAdapter (List<Comentari> list_comments){
        this.comments = list_comments;

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
        Comentari c = comments.get(position);

        holder.text.setText(c.gettext());
        holder.dia.setText(String.valueOf(c.getdata()));
        holder.c = c;
    }





    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text, dia;
        public Comentari c;
        private final Context context2;

        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.cv_text);
            dia = view.findViewById(R.id.cv_data);
            context2 = view.getContext();
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
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