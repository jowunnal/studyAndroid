package org.techtown.mission;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment2 extends Fragment {
    BookAdapter adapter;
    SQLiteDatabase db;
    BookDatabaseHelper helper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_item2,container,false);

        RecyclerView recyclerView=rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager manager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        adapter=new BookAdapter();
        recyclerView.setAdapter(adapter);

        inquiryDatabase(rootView);

        return rootView;
    }

    public void inquiryDatabase(ViewGroup container){
        helper=new BookDatabaseHelper(container.getContext());
        db=helper.getReadableDatabase();

        String sql="select _id,title,author,contents from book";
        Cursor cursor= db.rawQuery(sql,null);
        String title;
        String author;

        while(cursor.moveToNext()){
            title=cursor.getString(1);
            author=cursor.getString(2);
            adapter.addItem(new BookItem(title,author));
        }
        adapter.notifyDataSetChanged();
    }
}
