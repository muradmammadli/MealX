package com.example.foodx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private MealAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("Drawable",String.valueOf(R.drawable.burger));
        databaseHelper = new DatabaseHelper(this);
//        new MealDao().addMeal(databaseHelper,"Yarpaq dolması","5 AZN","Qoyun əti, düyü, baş soğan, keşniş, şüyüd, nanə, tənək yarpağı",R.drawable.yarpaqdolmasi);
//        new MealDao().addMeal(databaseHelper,"3 baci dolması","6 AZN","badımcan, yaşıl bibəri, pomidor, duz,istiot",R.drawable.ucbacidolmasi);
//        new MealDao().addMeal(databaseHelper,"Plov","4 AZN","düyü, süd, duz, zəfəran, kişmiş, xurma, kərə yağı, bal, qaymaq",R.drawable.plov);
//        new MealDao().addMeal(databaseHelper,"Ləvəngi","12 AZN","toyuq, soğan, qoz ləpəsi, alça turşusu, duz, istiot",R.drawable.levengi);


        List<MealModel> modelArrayList = new MealDao().mealModelArrayList(databaseHelper);

        adapter = new MealAdapter(this,modelArrayList);
        recyclerView.setAdapter(adapter);

    }
}