package com.devivekumar.databaserough;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    DatabaseHelper databaseHelper;
    TextView textView;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starts");
        databaseHelper = new DatabaseHelper(this);
        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();
                if(text.length()==0){
                    toastMessage("can't be blank");
                }
                else{
                    addData(text);
                    textView.setText("");
                }
                try  {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                e.printStackTrace();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(databaseHelper.isNullOrNot()){
                   Intent intent= new Intent(MainActivity.this,ListActivity.class);
                   startActivity(intent);
               }
            }
        });
    }

    public void addData(String newEntry) {
        boolean insertData= databaseHelper.addData(newEntry);
        if(insertData){
            toastMessage("success");
        }
        else{
            toastMessage("failed");
        }

    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
