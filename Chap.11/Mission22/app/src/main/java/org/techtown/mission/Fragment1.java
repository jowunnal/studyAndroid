package org.techtown.mission;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    EditText et1;
    EditText et2;
    EditText et3;
    SQLiteDatabase db;
    BookDatabaseHelper helper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment_item1,container,false);
        try{
        et1=rootView.findViewById(R.id.editTextTextPersonName);
        et2=rootView.findViewById(R.id.editTextTextPersonName2);;
        et3=rootView.findViewById(R.id.editTextTextPersonName3);;

        Button button=rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeInDatabase(rootView);
            }
        });}catch (Exception e){e.printStackTrace();}

        return rootView;
    }

    public void storeInDatabase(ViewGroup container){
        String title=et1.getText().toString();
        String author=et2.getText().toString();
        String contents=et3.getText().toString();

        helper= new BookDatabaseHelper(container.getContext());
        db=helper.getWritableDatabase();

        String sql="insert into book(title,author,contents) values ('"+title+"', '"+author+"', '"+contents+"')";
        db.execSQL(sql);
    }
}
