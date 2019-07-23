package com.devivekumar.databaserough;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";
ListView listView;
DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView=findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);
        populateListview();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this,EditDataActivity.class);
                String name= parent.getItemAtPosition(position).toString();
                intent.putExtra("id",position);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                toastMessage(Integer.toString(position));
                return true;
            }
        });
    }

    private void populateListview() {
        Cursor data= databaseHelper.getData();
        ArrayList<String> name=new ArrayList<>();
        data.moveToFirst();
        while (data.moveToNext()){
            name.add(data.getString(1));
        }
        ListAdapter listAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,name);
        listView.setAdapter(listAdapter);
    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
