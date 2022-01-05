package org.techtown.movie;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    ArrayList<Movie> items=new ArrayList<Movie>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.movie_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Movie movie){
        items.add(movie);
    }

    public void setItems(ArrayList<Movie> movies){
        this.items=movies;
    }

    public Movie getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv1;
        TextView tv2;
        ImageView iv1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1=itemView.findViewById(R.id.textView);
            tv2=itemView.findViewById(R.id.textView2);
            iv1=itemView.findViewById(R.id.imageView);

        }

        public void setItem(Movie item){
            tv1.setText(item.movieNm);
            tv2.setText(item.audiCnt+" 명");
            Glide.with(itemView).load(R.drawable.ic_launcher_background).into(iv1);
        }
    }

}
