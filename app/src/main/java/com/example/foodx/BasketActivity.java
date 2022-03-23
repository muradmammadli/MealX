package com.example.foodx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.foodx.Adapters.BasketAdapter;
import com.example.foodx.Database.AppDatabase;
import com.example.foodx.Database.Basket;

import java.util.List;

public class BasketActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button orderBtn,deleteBtn;
    private BasketAdapter adapter;
    private List<Basket> basketList;
    private AppDatabase db;
    private Toolbar basketToolbar;
    private ImageView addImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        initViews();
        basketToolbar.setTitle("Səbət");
        basketToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_icon_toolbar));
        setSupportActionBar(basketToolbar);
        deleteBtn.setOnClickListener(view -> {onDeleteButtonClicked();});
        db = AppDatabase.getDbInstance(this.getApplicationContext());
        basketList = db.userDao().getAllBasket();
        initRecyclerView();
        addImg.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BasketAdapter(this,basketList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
        orderBtn = findViewById(R.id.orderBtn);
        deleteBtn = findViewById(R.id.deletBtn);
        basketToolbar = findViewById(R.id.toolbarBasket);
        addImg = findViewById(R.id.addImg);
    }

    private void onDeleteButtonClicked(){
        for (Basket basket:basketList){
            db.userDao().deleteBasket(basket);
        }
        finish();
    }
}