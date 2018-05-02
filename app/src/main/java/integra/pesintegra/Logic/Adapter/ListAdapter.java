package integra.pesintegra.Logic.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.util.Log;
import android.content.Intent;

import java.util.List;

import integra.pesintegra.Logic.Clases.Post;
//import integra.pesintegra.Logic.Interface.CustomItemClickListener;
import integra.pesintegra.Presentation.CreateActivityActivity;
import integra.pesintegra.Presentation.LoginActivity;
import integra.pesintegra.Presentation.PostActivity;
import integra.pesintegra.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{

    private Context context;
    private List<Post> posts;

    public ListAdapter (List<Post> list_posts){
        this.posts = list_posts;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.activity_card, parent, false);
        CardView cv = (CardView) itemView.findViewById(R.id.cv);
        cv.setCardBackgroundColor(0xFFFFFFFF);
        final MyViewHolder mvh=  new MyViewHolder(itemView);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post p = posts.get(position);
        if(p.isShowed()){
            holder.titol.setText(p.getTitol());
            holder.dia.setText(String.valueOf(p.getDataIni()));
            holder.p = p;
        }
        else{

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titol, dia;
        public Post p;
        private final Context context2;

        public MyViewHolder(View view) {
            super(view);
            titol = (TextView) view.findViewById(R.id.cv_titol);
            dia = (TextView) view.findViewById(R.id.cv_dia);
            context2 = view.getContext();
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


}
