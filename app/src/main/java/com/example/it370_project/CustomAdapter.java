package com.example.it370_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList username, email,password;

    CustomAdapter(Activity activity, ArrayList username, ArrayList email, ArrayList password
                   ){
        this.activity = activity;
        this.context = context;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.showlayout, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.username_txt.setText(String.valueOf(username.get(position)));
        holder.email_txt.setText(String.valueOf(email.get(position)));
        holder.password_txt.setText(String.valueOf(password.get(position)));
        //Recyclerview onClickListener



    }

    @Override
    public int getItemCount() {
        return username.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView username_txt, email_txt, password_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username_txt = itemView.findViewById(R.id.username_txt);
            email_txt = itemView.findViewById(R.id.email_txt);
            password_txt = itemView.findViewById(R.id.password_txt);


        }

    }
}
