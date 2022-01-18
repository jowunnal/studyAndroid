package org.techtown.mission;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    ArrayList<BookItem> items=new ArrayList<BookItem>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookItem item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(BookItem item){
        items.add(item);
    }




    public static class ViewHolder extends RecyclerView.ViewHolder{
    TextView book_Title;
    TextView book_Author;
    ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            book_Title=itemView.findViewById(R.id.textView);
            book_Author=itemView.findViewById(R.id.textView2);
            iv=itemView.findViewById(R.id.imageView);
        }

        public void setItem(BookItem item){
            book_Title.setText(item.getTitle());
            book_Author.setText(item.getAuthor());
            iv.setImageResource(R.drawable.book);
        }
    }
}
