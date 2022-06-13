package com.example.lab2_jinesh_patel_c0851605_android;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab2_jinesh_patel_c0851605_android.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {
    TextView tvAllData;
    Button insert;
    Button update;
    Button refresh;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        refresh = findViewById(R.id.btnRefresh);
        delete = findViewById(R.id.btnDelete);

        tvAllData=findViewById(R.id.tvAllData);

        setUpButtons();
    }

    void setUpButtons() {

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInsertClick();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpdateClick();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDeleteClick();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRefreshClick();
            }
        });

    }
    private void btnInsertClick() {

    }

    private void btnUpdateClick() {

    }

    private void btnDeleteClick() {

    }

    private void btnRefreshClick() {

    }
}