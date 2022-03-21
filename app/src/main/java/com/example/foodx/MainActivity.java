package com.example.foodx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.foodx.Adapters.MealAdapter;
import com.example.foodx.Models.MealModel;
import com.example.foodx.SqliteDb.DatabaseHelper;
import com.example.foodx.SqliteDb.MealDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private MealAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        databaseHelper = new DatabaseHelper(this);
        initRecyclerView();


    }


    private void initRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<MealModel> modelArrayList = new MealDao().mealModelArrayList(databaseHelper);
        adapter = new MealAdapter(this,modelArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
    }
}