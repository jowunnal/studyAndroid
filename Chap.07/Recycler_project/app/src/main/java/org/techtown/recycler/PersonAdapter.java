package org.techtown.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{
    ArrayList<Person> items= new ArrayList<Person>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView= layoutInflater.inflate(R.layout.person_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;

        public ViewHolder(View itemView){
            super(itemView);

            textView1=itemView.findViewById(R.id.textView1);
            textView2=itemView.findViewById(R.id.textView2);
        }

        public void setItem(Person item){
            textView1.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }

    public void addItem(Person item){
        items.add(item);
    }
    public void setItem(Person item,int position){
        items.set(position,item);
    }
    public Person getItem(int position){
        return items.get(position);
    }
    public void setItems(ArrayList<Person> items){
        this.items=items;
    }
}
