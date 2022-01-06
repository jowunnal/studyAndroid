package org.techtown.mission;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class rssAdapter extends RecyclerView.Adapter<rssAdapter.ViewHolder>{
    ArrayList<rssItem> items=new ArrayList<rssItem>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.rss_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        rssItem item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(rssItem item){
        items.add(item);
    }

    public void setItems(ArrayList<rssItem> items){
        this.items=items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv1;
    TextView tv2;
    ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1=itemView.findViewById(R.id.textView1);
            tv2=itemView.findViewById(R.id.textView2);
            iv=itemView.findViewById(R.id.imageView);
        }
        public void setItem(rssItem item){
            tv1.setText(item.header);
            tv2.setText(item.info);
            iv.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}
