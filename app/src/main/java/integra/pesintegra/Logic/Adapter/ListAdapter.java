package integra.pesintegra.Logic.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import java.util.List;

import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{

    private List<Post> posts;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titol, dia;
        public View v;

        public MyViewHolder(View view) {
            super(view);
            titol = (TextView) view.findViewById(R.id.cv_titol);
            dia = (TextView) view.findViewById(R.id.cv_dia);
            v = view;
        }
    }

    public ListAdapter (List<Post> list_posts){
        this.posts = list_posts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card, parent, false);
        CardView cv = (CardView) itemView.findViewById(R.id.cv);
        cv.setCardBackgroundColor(0xFFFFFFFF);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post p = posts.get(position);
        holder.titol.setText(p.getTitol());
        holder.dia.setText(String.valueOf(p.getDataIni()));
    }

    public Post getItem(int position){
        return posts.get(position);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
