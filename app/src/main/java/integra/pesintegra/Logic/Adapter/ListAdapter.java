package integra.pesintegra.Logic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import com.google.gson.JsonObject;

import java.io.File;
import java.util.List;

import integra.pesintegra.Controllers.ControladorPresentacioAllPostsActivity;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Presentation.PostActivity;
import integra.pesintegra.R;
import integra.pesintegra.Services.PostService;
import integra.pesintegra.Services.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{

    private Context context;
    private static List<Post> posts;
    private float rate = 0;
    private ControladorPresentacioAllPostsActivity cpp;

    public ListAdapter (List<Post> list_posts, ControladorPresentacioAllPostsActivity cp){
        this.posts = list_posts;
        this.cpp = cp;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.activity_card, parent, false);
        CardView cv = itemView.findViewById(R.id.cv);
        cv.setCardBackgroundColor(0xFFFFFFFF);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post p = posts.get(position);
        holder.titol.setText(p.getTitol());
        holder.dia.setText(String.valueOf(p.getDataIni()));
        getRating(p.getId(), holder.rating);
        holder.rating.setRating(rate);
        //cpp.afegir_imatge(p.getId(), holder.icon_post, this);
        switch (p.getTipus()){
            case 'A':
                holder.icon_post.setBackgroundResource(R.drawable.activitat);
                break;
            case 'F':
                holder.icon_post.setBackgroundResource(R.drawable.feina);
                break;
            case 'H':
                holder.icon_post.setBackgroundResource(R.drawable.casa);
                break;
        }
        holder.p = p;
    }

    public void getRating(final String postId, final RatingBar rating) {
        PostService service = ServiceManager.getPostService();
        Call<JsonObject> createCall = service.getPostRating(postId);
        createCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                updateRating(response.body().get("puntuacio").toString().replace("\"", ""), rating);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void updateRating(String puntuacio, RatingBar rating) {
        rate =  Float.parseFloat(puntuacio);
        rating.setRating(rate);
    }

    public void posa_imatge(ImageView ff, Bitmap bitmapImage) {
        ff.setImageBitmap(bitmapImage);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titol, dia;
        public RatingBar rating;
        public Post p;
        private final Context context2;
        ImageView icon_post;

        public MyViewHolder(View view) {
            super(view);
            titol = view.findViewById(R.id.cv_titol);
            dia = view.findViewById(R.id.cv_dia);
            rating = view.findViewById(R.id.cv_ratingBar);
            context2 = view.getContext();
            icon_post = view.findViewById(R.id.icon);
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            final Intent intent = new Intent(context2, PostActivity.class);
            intent.putExtra("post", p);
            context2.startActivity(intent);
        }
    }




    public Post getItem(int position){
        return posts.get(position);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void removeHidden(List<String> postsH){
        if(postsH.size() > 0){
            Boolean end = false;
            int i = 0;
            while( !end){
                for(int j = 0;j < posts.size(); ++j){
                    if(posts.get(j).getId().equals(postsH.get(i))){
                        posts.remove(j);
                        j+=posts.size();
                    }
                }
                ++i;
                if(i>=postsH.size()) end = true;
            }
        }

    }
}
