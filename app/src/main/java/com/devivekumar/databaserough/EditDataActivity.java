package com.devivekumar.databaserough;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {
    private String name;
    EditText editText;
    Button updateButton;
    String newName;
    Button deleteButton;
    DatabaseHelper databaseHelper;
    private static final String TAG = "EditDataActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        editText= findViewById(R.id.textView2);
        Intent getIntent= getIntent();
        name= getIntent.getStringExtra("name");
        editText.setText(name);
        updateButton=findViewById(R.id.saveButton);
        editText=findViewById(R.id.textView2);
        databaseHelper=new DatabaseHelper(this);
        deleteButton=findViewById(R.id.deleteButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( editText.getText().toString().length()==0){
                    toastMessage("can't be blank");
                }
                else{
                    newName= editText.getText().toString();
                    databaseHelper.updateName(newName,name);
                    Log.d(TAG, "updating "+ name+" "+"to "+newName);
                    toastMessage("Success");
                    editText.setText("");
                    finish();
                }

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteName(name);
                toastMessage("done");
                editText.setText("");
                finish();
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
