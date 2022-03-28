package com.example.foodx;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.foodx.Adapters.MealAdapter;
import com.example.foodx.Models.MealModel;
import com.example.foodx.SqliteDb.DatabaseHelper;
import com.example.foodx.SqliteDb.MealDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private MealAdapter adapter;
    private Toolbar toolbar;
    private ImageView fab;
    private List<MealModel> modelArrayList;
    private int basketListSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        toolbar.setTitle("Yeməklər");
        setSupportActionBar(toolbar);
        databaseHelper = new DatabaseHelper(this);
        new MealDao().addMeal(databaseHelper,"Yarpaq dolması",5,"Qoyun əti, düyü, baş soğan, keşniş, şüyüd, nanə, tənək yarpağı",R.drawable.yarpaqdolmasi);
        new MealDao().addMeal(databaseHelper,"3 baci dolması",6,"badımcan, yaşıl bibəri, pomidor, duz,istiot",R.drawable.ucbacidolmasi);
        new MealDao().addMeal(databaseHelper,"Plov",4,"düyü, süd, duz, zəfəran, kişmiş, xurma, kərə yağı, bal, qaymaq",R.drawable.plov);
        new MealDao().addMeal(databaseHelper,"Ləvəngi",12,"toyuq, soğan, qoz ləpəsi, alça turşusu, duz, istiot",R.drawable.levengi);
        initRecyclerView();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("list-size"));

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,BasketActivity.class);
            startActivity(intent);
        });

    }


    private void initRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelArrayList = new MealDao().mealModelArrayList(databaseHelper);
        adapter = new MealAdapter(this,modelArrayList);
        recyclerView.setAdapter(adapter);
        Log.d("isEmpty", String.valueOf(modelArrayList.size()));
    }

    private void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fabImg);
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            basketListSize = intent.getIntExtra("BASKET_LIST_SIZE",0);
            Log.d("basketListSize", String.valueOf(basketListSize));
        }
    };
}