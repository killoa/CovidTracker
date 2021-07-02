package com.example.it370_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private EditText  username,email,password;
    ArrayList<String> alusername,alemail,alpassword;
    private Button registerButton,showButton,deleteAllButton;
    private DB_sqlite myDB;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulaire);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        registerButton = findViewById(R.id.btnRegister);
        showButton = findViewById(R.id.btnShow);
        deleteAllButton = findViewById(R.id.btnDeleteAll);
        myDB = new DB_sqlite(this);


        alusername = new ArrayList<>();
        alemail = new ArrayList<>();
        alpassword = new ArrayList<>();
        storeDataInArrays();

        insertUser();
        delete();
        showResults();
    }
    private void showResults(){
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customAdapter = new CustomAdapter(MainActivity2.this,  alusername, alemail, alpassword);
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));

            }
        });
    }
    private void insertUser(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean var = myDB.registerUser(username.getText().toString(),email.getText().toString() , password.getText().toString() );
                if(var){
                    Toast.makeText(MainActivity2.this, "User Registered Successfully !!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity2.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "no data",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                alusername.add(cursor.getString(0));
                alemail.add(cursor.getString(1));
                alpassword.add(cursor.getString(2));
                }
            }
    }
    /*private void displayData(){
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        })
    }*/
private void delete(){
deleteAllButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
if (myDB.deleteAll()== true){

    Toast.makeText(MainActivity2.this, "Deleted Successfully !!", Toast.LENGTH_SHORT).show();
}
    else Toast.makeText(MainActivity2.this, "Still not empty !!", Toast.LENGTH_SHORT).show();

    }
});


}
}

