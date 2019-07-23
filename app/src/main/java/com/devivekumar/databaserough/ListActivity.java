package com.devivekumar.databaserough;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";
    ListView listView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: starts");
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);
        populateListview();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, EditDataActivity.class);
                String name = parent.getItemAtPosition(position).toString();
                intent.putExtra("id", position);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });
    }

    private void populateListview() {
        Cursor data = databaseHelper.getData();
        ArrayList<String> name = new ArrayList<>();
        data.moveToFirst();
        do {
            name.add(data.getString(1));
        } while (data.moveToNext());
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name);
        listView.setAdapter(listAdapter);
    }

}
