package org.techtown.mission;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements ProduckOnClickListener{
    ArrayList<Product> items=new ArrayList<Product>();
    ProduckOnClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.product_item,parent,false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnProductClickListener(ProduckOnClickListener listener){
        this.listener=listener;
    }
    public Product getItem(int position){
        return items.get(position);
    }
    public void addItem(Product item){
        items.add(item);
    }

    @Override
    public void onItemClicked(ViewHolder holder, View v, int position) {
        if(listener!=null){
            listener.onItemClicked(holder,v,position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        ImageView iv;
        public ViewHolder(@NonNull View itemView,ProduckOnClickListener listener) {
            super(itemView);

            tv1=itemView.findViewById(R.id.textView1);
            tv2=itemView.findViewById(R.id.textView2);
            tv3=itemView.findViewById(R.id.textView3);
            iv=itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener!=null){
                        listener.onItemClicked(ViewHolder.this,v,position);
                    }
                }
            });
        }

        public void setItem(Product item){
            tv1.setText(item.getName());
            tv2.setText(item.getPrice());
            tv3.setText(item.getInfo());
            iv.setImageResource(item.getResId());
        }
    }
}
